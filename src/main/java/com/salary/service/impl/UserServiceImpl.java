package com.salary.service.impl;

import com.salary.dao.UserMapper;
import com.salary.pojo.JWT;
import com.salary.pojo.User;
import com.salary.service.UserService;

import javax.annotation.Resource;

/**
 * @author Jialin
 * @create 2023-10-16 13:09
 */
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public JWT createEmployee(User user) {
        String userId = user.getId();
        User selectedUser = userMapper.selectUserById(userId);
        long count = 0;
        while(selectedUser != null) {
            count++;
            selectedUser = userMapper.selectUserById(userId + count);
        }
        user.setId(userId + count);
        userMapper.insert(user);
        return new JWT(user.getId(), "employee");
    }
}
