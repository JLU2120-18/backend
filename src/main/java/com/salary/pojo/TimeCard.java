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
public class TimeCard {
    private String id;
    private String employeeId;
    private Boolean isSave;
    private String startTime;
    private String endTime;
    private BigDecimal duration;
}
