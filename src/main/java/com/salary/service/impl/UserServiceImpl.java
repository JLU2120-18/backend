package com.salary.service.impl;

import com.salary.dao.AuthMapper;
import com.salary.dao.UserMapper;
import com.salary.dto.UserDTO;
import com.salary.common.Page;
import com.salary.pojo.Auth;
import com.salary.pojo.User;
import com.salary.service.UserService;
import com.salary.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AuthMapper authMapper;

    /**
     * 判断是否有权限访问
     *
     * @param jwt
     * @return
     */
    private String hasPermissionToAccess(String jwt, String role) {
        // 1.解析jwt
        Claims claims = JwtUtils.parseToken(jwt);
        String userId = claims.get("id").toString();
        String userRole = claims.get("role").toString();

        // 2.判断当前操作用户权限是否为role
        if (userRole == null || !userRole.equals(role)) {
            return null;
        }

        // 3.查询数据库中的role和传过来的role是否一致
        String selectedRole = authMapper.selectRoleById(userId);
        if (selectedRole == null || !selectedRole.equals(userRole)) {
            return null;
        }

        return userId;
    }

    /**
     * 添加员工
     * @param user
     * @return
     */
    @Override
    public User createEmployee(UserDTO user) {
        // 1.获取jwt并解析, 判断是否有权限
        String jwt = user.getJwt();
        if(hasPermissionToAccess(jwt, "payroll") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.获取员工originId
        String userId = user.getId();

        // 3.根据userId查员工
        User selectedUser = userMapper.selectUserById(userId);

        // 4.定义count用来解决用户重名
        long count = 0;
        while(selectedUser != null) {
            count++;
            selectedUser = userMapper.selectUserById(userId + count);
        }

        // 5.当前userId + count不重名
        if(count > 0) {
            userId += count;
        }
        user.setId(userId);
        Auth auth = new Auth(userId, user.getUsername(), DigestUtils.md5DigestAsHex(user.getPhone().getBytes()), "employee");

        // 6.数据库插入user和auth
        int success = userMapper.insert(user);
        success += authMapper.insert(auth);

        if(success < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return user;
    }

    /**
     * 查询单个员工信息
     * @param id
     * @param jwt
     * @return
     */
    @Override
    public User getEmployee(String jwt, String id) {
        User user = null;

        // 1.解析jwt
        Claims claims = JwtUtils.parseToken(jwt);
        String userId = claims.get("id").toString();
        String role = claims.get("role").toString();

        // 2.如果id为空, 则说明当前查询为员工自己信息
        if(id == null || id.length() == 0) {
            user = userMapper.selectUserById(userId);
            return user;
        }

        // 3.id不为空, 则说明当前查询为管理员操作, 需要判断role是否为payroll
        if(!"payroll".equals(role)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            String selectedRole = authMapper.selectRoleById(userId);

            // 4.判断和数据库role信息是否一致
            if(selectedRole == null || !selectedRole.equals(role)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                user = userMapper.selectUserBaseInfoAndPaymentById(id);
                return user;
            }
        }
    }

    /**
     * 查询以id为前缀的员工信息分页
     * @param jwt
     * @param id
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getEmployees(String jwt, String id, long pageIndex, long pageSize) {
        // 1.判断是否有权限
        if(hasPermissionToAccess(jwt, "payroll") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.分页查询以id为前缀的员工信息
        pageIndex = (pageIndex > 0) ? pageIndex : 1L;
        pageSize = (pageSize > 0) ? pageSize : 10L;
        Page<User> page = new Page<>();
        long total = userMapper.countPrefixWithId(id);
        long pageEnd = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
        pageIndex = Math.min(pageIndex, pageEnd);
        page.setData(userMapper.pageUserBaseInfoPrefixWithId(id, (pageIndex - 1) * pageSize, pageSize));
        page.setTotal(total);
        page.setCurrent(pageIndex);
        page.setSize(pageSize);
        return page;
    }

    /**
     * 查询以id为前缀的所有员工id
     * @param id
     * @return
     */
    @Override
    public Page<String> suggestIds(String jwt, String id) {
        // 1.判断是否有权限
        if(hasPermissionToAccess(jwt, "payroll") == null && hasPermissionToAccess(jwt, "commission") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.判断id是否合法
        if(id == null || id.length() == 0) {
            return new Page<>(Collections.emptyList(), null, null, null);
        }

        Page<String> page = new Page<>();
        page.setData(userMapper.selectIdsPrefixWithId(id));
        return page;
    }

    /**
     * 更新员工信息
     * @param user
     */
    @Override
    public void updateEmployee(UserDTO user) {
        // 1.获取jwt并解析, 判断是否有权限
        String jwt = user.getJwt();
        if(hasPermissionToAccess(jwt, "payroll") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.获取员工id, 判断id是否合法
        String id = user.getId();
        if(id == null || id.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id更新员工信息
        int success = userMapper.updateUserById(user);

        // 4.更新失败, 抛出异常
        if(success < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 员工更改发薪方式
     * @param user
     */
    @Override
    public void updatePayment(UserDTO user) {
        // 1.获取jwt并解析
        String jwt = user.getJwt();
        Claims claims = JwtUtils.parseToken(jwt);
        String userId = claims.get("id").toString();
        String role = claims.get("role").toString();

        // 2.判断userId是否合法
        if(userId == null || userId.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id更新员工发薪方式
        user.setId(userId);
        int success = userMapper.updatePaymentById(user);

        // 4.更新失败, 抛出异常
        if(success < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 删除员工
     * @param user
     */
    @Override
    public void deleteEmployee(UserDTO user) {
        // 1.获取jwt并解析, 判断是否有权限
        String jwt = user.getJwt();
        if(hasPermissionToAccess(jwt, "payroll") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 2.获取员工id, 判断id是否合法
        String id = user.getId();
        if(id == null || id.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.查询待删除员工role是否为payroll
        String role = authMapper.selectRoleById(id);
        if("payroll".equals(role)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 4.根据id删除对应员工
        int success = authMapper.deleteAuthById(id);
        success += userMapper.deleteUserById(id);

        // 5.删除失败, 抛出异常
        if(success < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
