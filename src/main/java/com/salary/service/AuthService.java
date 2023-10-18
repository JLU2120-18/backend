package com.salary.service;

import com.salary.dto.AuthDTO;

/**
 * @author Jialin
 * @create 2023-10-17 15:28
 */
public interface AuthService {
    AuthDTO login(AuthDTO authDTO);
}
