package com.salary.service;

import com.salary.common.Page;
import com.salary.pojo.User;

import java.util.List;

/**
 * @author Jialin
 * @create 2023-10-16 13:07
 */
public interface UserService {
    User createEmployee(User user);
    User getEmployee(String id, String jwt);
    void updateEmployee(User user);
    void deleteEmployee(User user);
    Page<User> getEmployees(String jwt, String id, long pageIndex, long pageSize);
    Page<String> suggestIds(String id);
}
