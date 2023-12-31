package com.salary.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private String employeeId;
    private String employeeName;
    private String startTime;
    private String endTime;
    private BigDecimal salary;

}
