package com.salary.service;

import com.salary.reports.EmployeeDurationReport;
import com.salary.reports.EmployeeSalaryReport;
import com.salary.vo.ReportVO;

public interface AdminReportService {
    ReportVO<EmployeeDurationReport[]> createDuration(String startTime, String endTime, String employeeId);
    ReportVO<EmployeeSalaryReport[]> createSalary(String startTime, String endTime, String employeeId);
}
