package com.salary.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
public class TimeCard {
    private String id;
    private String employeeId;
    private String projectName;
    private String isSave;
    private String startTime;
    private String endTime;
    private BigDecimal duration;

    public TimeCard() {
    }

    public TimeCard(String id, String employeeId, String projectName, String isSave, String startTime, String endTime, BigDecimal duration) {
        this.id = id;
        this.employeeId = employeeId;
        this.projectName = projectName;
        this.isSave = isSave;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}
