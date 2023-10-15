package com.salary.reports;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class SalaryReport {
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private Integer salary;

    public SalaryReport() {
    }

    public SalaryReport(String employeeId, String employeeName, String startTime, String endTime, Integer salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.salary = salary;
    }
}
