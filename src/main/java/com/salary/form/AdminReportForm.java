package com.salary.form;

import lombok.Data;

@Data
public class AdminReportForm {
    private String type;
    private String jwt;
    private String startTime;
    private String endTime;
    private String employeeId;
}
