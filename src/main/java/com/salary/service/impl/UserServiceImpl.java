package com.salary.service.impl;

import com.salary.dao.AuthMapper;
import com.salary.dao.UserMapper;
import com.salary.pojo.Auth;
import com.salary.pojo.JWT;
import com.salary.pojo.User;
import com.salary.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
}
