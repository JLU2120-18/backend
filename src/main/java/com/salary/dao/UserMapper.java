package com.salary.dao;

import com.salary.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jialin
 * @create 2023-10-16 13:24
 */

@Mapper
public interface UserMapper {
    int insert(User user);
    User selectUserById(String id);
}
