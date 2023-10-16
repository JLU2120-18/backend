package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private String id;
    private String username;
    private String password;

    private String role;
}
