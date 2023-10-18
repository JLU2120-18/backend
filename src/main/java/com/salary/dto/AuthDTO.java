package com.salary.dto;

import com.salary.pojo.Auth;
import lombok.Data;

/**
 * @author Jialin
 * @create 2023-10-18 12:26
 */

public class AuthDTO extends Auth {
    private Boolean remember;

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }
}
