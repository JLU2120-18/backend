package com.salary.pojo;

import lombok.Data;

/**
 * @author Jialin
 * @create 2023-10-16 13:00
 */

@Data
public class JWT {
    private String id;
    private String role;

    public JWT() {
    }

    public JWT(String id, String role) {
        this.id = id;
        this.role = role;
    }
}
