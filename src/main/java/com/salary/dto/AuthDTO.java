package com.salary.dto;

import com.salary.pojo.Auth;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Jialin
 * @create 2023-10-18 12:26
 */

@Component
public class AuthDTO extends Auth {
    private Boolean remember;

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }
}
