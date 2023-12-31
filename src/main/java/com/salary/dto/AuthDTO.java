package com.salary.dto;

import com.salary.pojo.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO extends Auth {
    private Boolean remember;
    private String jwt;
}
