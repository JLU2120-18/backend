package com.salary.controller;

import com.salary.dto.AuthDTO;
import com.salary.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/napi/auth")
public class AuthController {
    @Resource
    private AuthService authService;

    @PostMapping("/login")
    public AuthDTO login(@RequestBody AuthDTO authDTO) {
        Boolean remember = authDTO.getRemember();
        authDTO.setRemember((remember == null) ? Boolean.FALSE : remember);
        return authService.login(authDTO);
    }
}
