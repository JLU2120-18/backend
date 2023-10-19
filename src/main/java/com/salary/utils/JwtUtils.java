package com.salary.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Component
public class JwtUtils {
    private static String secretKey;

    @Value("${jwt.secretKey}") // 从配置文件中获取密钥
    public void setSecretKey(String secretKey) {
        JwtUtils.secretKey = secretKey;
    }

    // 创建 JWT
    public static String createToken(String id, String role) {
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000); // 设置过期时间为1小时后
        return Jwts.builder().setSubject("JWTToken") // 设置主题(Subject)
                .setExpiration(expiration)
                .claim("id", id).claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 使用指定的算法和密钥签名
                .compact();
    }

    // 创建 JWT
    public static String createToken(String id, String role, Date expiration) {
        return Jwts.builder().setSubject("JWTToken") // 设置主题(Subject)
                .setExpiration(expiration)
                .claim("id", id).claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 使用指定的算法和密钥签名
                .compact();
    }

    // 解析 JWT
    public static Claims parseToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
