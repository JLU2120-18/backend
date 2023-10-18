package com.salary.service;

import com.salary.reports.DurationReport;
import com.salary.reports.ProjectDurationReport;
import com.salary.reports.SalaryReport;
import com.salary.reports.VacationReport;
import com.salary.vo.ReportVO;

/**
 * 描述：
 */
public interface EmployeeReportService {
    ReportVO<DurationReport[]> createDuration(String startTime, String endTime, String employeeId);
    ReportVO<ProjectDurationReport[]> createProjDuration(String timeCardId, String startTime, String endTime, String employeeId);
    ReportVO<VacationReport[]> createVacation(String startTime, String endTime, String employeeId);
    ReportVO<SalaryReport[]> createSalary(String startTime, String endTime, String employeeId);
}