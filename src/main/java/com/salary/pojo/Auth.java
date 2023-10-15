package com.salary.pojo;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class Auth {
    private String id;
    private String username;
    private String password;

    private String role;

    public Auth() {
    }

    public Auth(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
