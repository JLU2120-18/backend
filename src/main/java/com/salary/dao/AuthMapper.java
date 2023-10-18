package com.salary.dao;

import com.salary.pojo.Auth;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jialin
 * @create 2023-10-16 20:15
 */

@Mapper
public interface AuthMapper {
    int insert(Auth auth);
    String selectRoleById(String id);
    Auth selectAuthById(String id);
}
