package com.salary.reports;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
public class DurationReport {
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private BigDecimal duration;

    public DurationReport() {
    }

    public DurationReport(String employeeId, String employeeName, String startTime, String endTime, BigDecimal duration) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}
