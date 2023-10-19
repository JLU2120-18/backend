package com.salary.form;

import lombok.Data;

/**
 * 描述：
 */
@Data
public class EmployeeReportForm {
    private String type;
    private String jwt;
    private String timeCardId;
    private String startTime;
    private String endTime;
}
