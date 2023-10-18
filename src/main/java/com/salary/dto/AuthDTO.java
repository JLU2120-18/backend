package com.salary.dto;

import com.salary.pojo.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Jialin
 * @create 2023-10-18 12:26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO extends Auth {
    private Boolean remember;
    private String jwt;
}
