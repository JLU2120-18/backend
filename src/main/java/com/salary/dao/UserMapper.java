package com.salary.dao;

import com.salary.pojo.User;

/**
 * @author Jialin
 * @create 2023-10-16 13:24
 */
public interface UserMapper {
    int insert(User user);
    User selectUserById(String id);

}
