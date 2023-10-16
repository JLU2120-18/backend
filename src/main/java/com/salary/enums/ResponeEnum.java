package com.salary.enums;

import lombok.Getter;

@Getter
public enum ResponeEnum {
    ERROR("HTTP 400");
    final String desc;

    ResponeEnum(String desc) {
        this.desc = desc;
    }
}
