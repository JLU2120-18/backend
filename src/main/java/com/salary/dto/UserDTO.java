package com.salary.dto;

import com.salary.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jialin
 * @create 2023-10-19 15:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends User {
    private String jwt;
}
