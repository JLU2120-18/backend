package com.salary.reports;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
public class ProjectDurationReport {
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private String projectName;
    private BigDecimal duration;

    public ProjectDurationReport() {
    }

    public ProjectDurationReport(String employeeId, String employeeName, String startTime, String endTime, String projectName, BigDecimal duration) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectName = projectName;
        this.duration = duration;
    }
}
