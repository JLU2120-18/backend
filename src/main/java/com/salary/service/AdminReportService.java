package com.salary.service;

import com.salary.dao.AdminReportMapper;
import com.salary.pojo.Timecard;
import com.salary.reports.EmployeeDurationReport;
import com.salary.reports.EmployeeSalaryReport;
import com.salary.vo.AdminReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：薪资管理员service层
 */
@Service
public class AdminReportService {

    @Autowired
    AdminReportMapper adminReportMapper;

    public AdminReportVO<EmployeeDurationReport[]> createDuration(String startTime, String endTime, String employeeId) {
        //传入id为null，创建全部，否则指定
        if (employeeId == null){
            return AdminReportVO.error();
        }else{
            List<Timecard> timecards = adminReportMapper.selectTimeCard(employeeId);
            int duration = 0;
            for(Timecard timecard : timecards){
                duration += timecard.getDuration();
            }
            String name = adminReportMapper.selectName(employeeId);
            EmployeeDurationReport[] employeeDurationReports = new EmployeeDurationReport[1];
            EmployeeDurationReport employeeDurationReport = new EmployeeDurationReport(
                employeeId,name,startTime,endTime,duration
            );
            employeeDurationReports[0] = employeeDurationReport;
            return AdminReportVO.success(employeeDurationReports);
        }
    }



    public AdminReportVO<EmployeeSalaryReport[]> createSalary(String startTime, String endTime, String employeeId) {
        return AdminReportVO.error();
    }
}
