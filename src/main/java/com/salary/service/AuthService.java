package com.salary.service;

import com.salary.dto.AuthDTO;
import com.salary.dto.UserDTO;
import com.salary.pojo.Auth;
import com.salary.pojo.User;

/**
 * @author Jialin
 * @create 2023-10-17 15:28
 */
public interface AuthService {
    UserDTO login(AuthDTO authDTO);
}
