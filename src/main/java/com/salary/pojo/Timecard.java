package com.salary.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：t
 */
@Data
public class TimeCard {
    private String id;
    private String employeeId;
    private String isSave;
    private String startTime;
    private String endTime;
    private BigDecimal duration;

    public TimeCard() {
    }

    public TimeCard(String id, String employeeId, String isSave, String startTime, String endTime, BigDecimal duration) {
        this.id = id;
        this.employeeId = employeeId;
        this.isSave = isSave;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}
