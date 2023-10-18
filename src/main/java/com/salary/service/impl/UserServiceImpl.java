package com.salary.service.impl;

import com.salary.dao.AuthMapper;
import com.salary.dao.UserMapper;
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

/**
 * @author Jialin
 * @create 2023-10-16 13:09
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AuthMapper authMapper;

    /**
     * 创建员工
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
        userId += count;
        user.setId(userId);
        Auth auth = new Auth(userId, user.getUsername(), DigestUtils.md5DigestAsHex(user.getPhone().getBytes()), "employee");

        // 5.数据库插入user和auth
        userMapper.insert(user);
        authMapper.insert(auth);

        return user;
    }

    /**
     * 获取员工信息
     * @param jwt
     * @return
     */
    @Override
    public User getEmployee(String id, String jwt) {
        User user = null;

        // 1.解析jwt
        Claims claims = JwtUtils.parseToken(jwt);
        String userId = claims.get("id").toString();
        String role = claims.get("role").toString();

        // 2.如果id为空, 则说明当前查询为员工自己信息
        if(id == null || "".equals(id)) {
            user = userMapper.selectUserById(id);
            return user;
        }

        // 3.id不为空, 则说明当前查询为管理员操作, 需要判断role是否为payroll
        if(!"payroll".equals(role)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            String selectedRole = authMapper.selectRoleById(id);

            // 4.判断和数据库role信息是否一致
            if(selectedRole == null || !selectedRole.equals(role)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                user = userMapper.selectUserById(id);
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
        if(id == null || "".equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id删除对应员工
        int success = userMapper.deleteUserById(id);

        // 4.删除失败, 抛出异常
        if(success == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
