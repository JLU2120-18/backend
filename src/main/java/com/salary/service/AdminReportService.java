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
            //查询考勤卡，计算总时长
            List<Timecard> timecards = adminReportMapper.selectTimeCard(employeeId);
            int duration = 0;
            for(Timecard timecard : timecards){
                duration += timecard.getDuration();
            }
            //查询员工名字
            String name = adminReportMapper.selectName(employeeId);
            //封装信息返回
            EmployeeDurationReport[] employeeDurationReports = new EmployeeDurationReport[1];
            EmployeeDurationReport employeeDurationReport = new EmployeeDurationReport(
                employeeId,name,startTime,endTime,duration
            );
            employeeDurationReports[0] = employeeDurationReport;
            return AdminReportVO.success(employeeDurationReports);
        }
    }



    public AdminReportVO<EmployeeSalaryReport[]> createSalary(String startTime, String endTime, String employeeId) {
        //获取User
        //获取name
        //根据员工类型计算salary
        //时薪
        //获取考勤卡，要注意是否可以加班以及加班工资计算
        //受雇
        //固定工资直接获取salary
        //委托
        //获取固定工资
        //计算佣金
        return AdminReportVO.error();
    }
}
