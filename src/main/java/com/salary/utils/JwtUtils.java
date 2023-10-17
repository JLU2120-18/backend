package com.salary.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}") // 从配置文件中获取密钥
    private static String secretKey;

    // 创建 JWT
    public static String createToken(String id, String role) {
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000); // 设置过期时间为1小时后
        return Jwts.builder().setSubject("JWTToken") // 设置主题(Subject)
                .setExpiration(expiration)
                .claim("id", id).claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 使用指定的算法和密钥签名
                .compact();
    }

    // 解析 JWT
    public static Claims parseToken(String jwt) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) {
        String id = "lulongjie";
        String role = "payroll";
        String token = JwtUtils.createToken(id,role);
        System.out.println(token);
    }
}
