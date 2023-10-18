package com.salary.service.impl;

import com.salary.dao.AuthMapper;
import com.salary.dao.UserMapper;
import com.salary.pojo.Auth;
import com.salary.common.Page;
import com.salary.pojo.User;
import com.salary.service.UserService;
import com.salary.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AuthMapper authMapper;

    /**
     * 添加员工
     * @param user
     * @return
     */
    @Override
    public User createEmployee(User user) {
        // 1.获取员工originId
        String userId = user.getId();

        // 2.根据userId查员工
        User selectedUser = userMapper.selectUserById(userId);

        // 3.定义count用来解决用户重名
        long count = 0;
        while(selectedUser != null) {
            count++;
            selectedUser = userMapper.selectUserById(userId + count);
        }

        // 4.当前userId + count不重名
        if(count > 0) {
            userId += count;
        }
        user.setId(userId);
        Auth auth = new Auth(userId, user.getUsername(), DigestUtils.md5DigestAsHex(user.getPhone().getBytes()), "employee");

        // 5.数据库插入user和auth
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
            user = userMapper.selectUserBaseInfoAndPaymentById(userId);
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
     * 更新员工信息
     * @param user
     */
    @Override
    public void updateEmployee(User user) {
        // 1.获取员工id
        String id = user.getId();

        // 2.判断id是否合法
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
     * 删除员工
     * @param user
     */
    @Override
    public void deleteEmployee(User user) {
        // 1.获取员工id
        String id = user.getId();

        // 2.判断id是否合法
        if(id == null || id.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id删除对应员工
        int success = authMapper.deleteAuthById(id);
        success += userMapper.deleteUserById(id);

        // 4.删除失败, 抛出异常
        if(success < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
        // 1.解析jwt
        Claims claims = JwtUtils.parseToken(jwt);
        String userId = claims.get("id").toString();
        String role = claims.get("role").toString();

        // 2.判断当前操作用户是否为payroll
        if(role == null || !"payroll".equals(role)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.判断当前操作用户在数据库中的role是否与传进来的role一致
        String selectedRole = authMapper.selectRoleById(userId);
        if(selectedRole == null || !selectedRole.equals(role)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 4.分页查询以id为前缀的员工信息
        Page<User> page = new Page<>();
        page.setData(userMapper.pageUserBaseInfoPrefixWithId(id, (pageIndex - 1) * pageSize, pageSize));
        page.setTotal(userMapper.countPrefixWithId(id));
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
    public Page<String> suggestIds(String id) {
        // 判断id是否合法
        if(id == null || id.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Page<String> page = new Page<>();
        page.setData(userMapper.selectIdsPrefixWithId(id));
        return page;
    }
}
