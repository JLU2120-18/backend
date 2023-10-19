package com.salary.controller;

import com.salary.dto.AuthDTO;
import com.salary.dto.UserDTO;
import com.salary.pojo.Auth;
import com.salary.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jialin
 * @create 2023-10-17 15:20
 */

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    private AuthService authService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody AuthDTO authDTO) {
        return authService.login(authDTO);
    }
}
