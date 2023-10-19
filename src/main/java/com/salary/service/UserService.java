package com.salary.service;

import com.salary.common.Page;
import com.salary.dto.UserDTO;
import com.salary.pojo.User;

public interface UserService {
    User createEmployee(UserDTO user);
    User getEmployee(String jwt, String id);
    Page<User> getEmployees(String jwt, String id, long pageIndex, long pageSize);
    Page<String> suggestIds(String jwt, String id);
    void updateEmployee(UserDTO user);
    void deleteEmployee(UserDTO user);
}
