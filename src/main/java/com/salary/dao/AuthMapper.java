package com.salary.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    int insert(Auth auth);
    String selectRoleById(String id);
    Auth selectAuthById(String id);
    int deleteAuthById(String id);
}
