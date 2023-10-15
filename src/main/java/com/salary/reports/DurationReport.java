package com.salary.reports;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class DurationReport {
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private Integer duration;

    public DurationReport() {
    }

    public DurationReport(String employeeId, String employeeName, String startTime, String endTime, Integer duration) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}