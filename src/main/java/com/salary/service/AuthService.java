package com.salary.service;

import com.salary.dto.AuthDTO;
import com.salary.dto.UserDTO;
import com.salary.pojo.Auth;

/**
 * @author Jialin
 * @create 2023-10-17 15:28
 */
public interface AuthService {
    UserDTO login(AuthDTO authDTO);
}
