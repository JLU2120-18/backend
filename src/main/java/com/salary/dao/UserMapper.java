package com.salary.dao;

import com.salary.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User user);
    User selectUserById(String id);
    User selectUserBaseInfoById(String id);
    User selectUserBaseInfoAndPaymentById(String userId);
    String selectNameById(String employeeId);
    int deleteUserById(String id);
    int updateUserById(User user);
    List<String> selectIdsPrefixWithId(String id);
    long countPrefixWithId(String id);
    List<User> pageUserBaseInfoPrefixWithId(String id, long pageBegin, long pageSize);
}
