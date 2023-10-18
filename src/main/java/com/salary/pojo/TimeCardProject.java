package com.salary.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
public class TimeCardProject {
    private String id;
    private String projectName;
    private BigDecimal duration;
}
