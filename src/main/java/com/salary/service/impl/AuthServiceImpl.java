package com.salary.service.impl;

import com.salary.dao.AuthMapper;
import com.salary.dto.AuthDTO;
import com.salary.dto.UserDTO;
import com.salary.pojo.Auth;
import com.salary.pojo.User;
import com.salary.service.AuthService;
import com.salary.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Jialin
 * @create 2023-10-17 15:28
 */

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthMapper authMapper;

    /**
     * 登录
     * @param authDTO
     * @return
     */
    @Override
    public UserDTO login(AuthDTO authDTO) {
        UserDTO userDTO = null;

        // 1.获取auth相关属性
        String id = authDTO.getId();
        String password = DigestUtils.md5DigestAsHex(authDTO.getPassword().getBytes());
        Boolean remember = authDTO.getRemember();

        // 2.后端加强验证, 防止输入为空
        if(id == null || id.length() == 0 || password == null || password.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 3.根据id查询auth, 判断密码是否正确
        Auth selectedAuth = authMapper.selectAuthById(id);
        if(selectedAuth == null || !selectedAuth.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 4.返回userDTO
        userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(selectedAuth.getUsername());
        userDTO.setRole(selectedAuth.getRole());
        String jwt = remember ? JwtUtils.createToken(id, userDTO.getRole(), new Date(System.currentTimeMillis() + 14 * 24 * 3600 * 1000)) : JwtUtils.createToken(id, userDTO.getRole());
        userDTO.setJwt(jwt);
        return userDTO;
    }
}
