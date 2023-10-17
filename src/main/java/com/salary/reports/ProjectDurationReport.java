package com.salary.reports;

import com.salary.pojo.TimeCardProjectData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDurationReport {
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private TimeCardProjectData[] data;

}

