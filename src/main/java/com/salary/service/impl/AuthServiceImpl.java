package com.salary.service.impl;

import com.salary.dao.AuthMapper;
import com.salary.dto.AuthDTO;
import com.salary.pojo.Auth;
import com.salary.service.AuthService;
import com.salary.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthMapper authMapper;

    /**
     * 登录
     * @param auth
     * @return
     */
    @Override
    public AuthDTO login(AuthDTO auth) {
        AuthDTO authDTO = null;

        // 1.获取auth相关属性
        String id = auth.getId();
        String password = DigestUtils.md5DigestAsHex(auth.getPassword().getBytes());
        Boolean remember = auth.getRemember();

        // 2.后端加强验证, 防止输入为空
        if(id == null || id.length() == 0 || password == null || password.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id查询auth, 判断密码是否正确
        Auth selectedAuth = authMapper.selectAuthById(id);
        if(selectedAuth == null || !selectedAuth.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 4.返回authDTO
        authDTO = new AuthDTO();
        authDTO.setId(id);
        authDTO.setUsername(selectedAuth.getUsername());
        authDTO.setRole(selectedAuth.getRole());
        String jwt = remember ? JwtUtils.createToken(id, authDTO.getRole(), new Date(System.currentTimeMillis() + 14 * 24 * 3600 * 1000)) : JwtUtils.createToken(id, authDTO.getRole());
        authDTO.setJwt(jwt);
        return authDTO;
    }
}
