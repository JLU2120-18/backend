package com.salary.reports;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class VacationReport {
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;

    public VacationReport() {
    }

    public VacationReport(String employeeId, String employeeName, String startTime, String endTime) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
