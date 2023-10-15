package com.salary.reports;

import lombok.Data;

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
    private Integer duration;

    public ProjectDurationReport() {
    }

    public ProjectDurationReport(String employeeId, String employeeName, String startTime, String endTime, String projectName, Integer duration) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectName = projectName;
        this.duration = duration;
    }
}
