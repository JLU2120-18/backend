package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeCardProjectData {
    private String projectName;
    private BigDecimal duration;
}
