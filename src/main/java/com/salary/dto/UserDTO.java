package com.salary.dto;

import com.salary.pojo.User;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Jialin
 * @create 2023-10-17 15:24
 */

@Component
public class UserDTO extends User {
    private String role;
    private String jwt;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
