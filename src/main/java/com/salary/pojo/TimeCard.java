package com.salary.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeCard implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String employeeId;
    private String isSave;
    private String startTime;
    private String endTime;
    private BigDecimal duration;
}
