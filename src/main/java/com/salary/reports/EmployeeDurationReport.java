package com.salary.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDurationReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private BigDecimal duration;
}
