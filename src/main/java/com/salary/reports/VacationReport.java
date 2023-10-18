package com.salary.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationReport {
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private Integer days;
}
