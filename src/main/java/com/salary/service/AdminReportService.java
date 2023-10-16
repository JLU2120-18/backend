package com.salary.service;

import com.salary.dao.AdminReportMapper;
import com.salary.dao.UserMapper;
import com.salary.pojo.TimeCard;
import com.salary.pojo.User;
import com.salary.reports.EmployeeDurationReport;
import com.salary.reports.EmployeeSalaryReport;
import com.salary.vo.AdminReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：薪资管理员service层
 */
@Service
public class AdminReportService {

    @Autowired
    AdminReportMapper adminReportMapper;

    @Autowired
    UserMapper userMapper;

    public AdminReportVO<EmployeeDurationReport[]> createDuration(String startTime, String endTime, String employeeId) {
        //传入id为null，创建全部，否则指定
        if (employeeId == null){
//            return AdminReportVO.error();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            //查询考勤卡，计算总时长
            List<TimeCard> timecards = adminReportMapper.selectTimeCard(employeeId);
            BigDecimal duration = new BigDecimal(0);
            for(TimeCard timecard : timecards){
                duration = duration.add(timecard.getDuration());
            }
            //查询员工名字
            String name = adminReportMapper.selectName(employeeId);
            if (name == null)
//                return AdminReportVO.error();
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
        User user = userMapper.selectUserById(employeeId);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        //获取name
        String name = user.getUsername();
        //根据员工类型计算salary
        String type = user.getType();
        BigDecimal sum = new BigDecimal(0);
        //计算出去税收百分比
        BigDecimal taxRate = BigDecimal.valueOf(1).subtract(user.getTaxRate());
        BigDecimal cast = user.getOtherCast();
        //时薪
        if (type.equals("wage")){
            //获取考勤卡，要注意是否可以加班以及加班工资计算
            List<TimeCard> timecards = adminReportMapper.selectTimeCard(employeeId);
            for(TimeCard timecard : timecards){
                BigDecimal x = new BigDecimal(8);
                BigDecimal duration = timecard.getDuration();
                BigDecimal hourWage = user.getSalary();
                if(duration.compareTo(x) < 0){
                    sum =  hourWage.multiply(duration).multiply(taxRate).subtract(cast);
                }else{
                    int limit = user.getDurationLimit();
                    if (limit != 0){
                        sum = hourWage.multiply(x).multiply(taxRate).subtract(cast);
                    }else{
                        BigDecimal cnt = duration.subtract(x);
                        sum = hourWage.multiply(x).
                                add(hourWage.multiply(BigDecimal.valueOf(1.5)).
                                        multiply(cnt)).multiply(taxRate).subtract(cast);
                    }
                }
            }
        }
        //受雇
        //固定工资直接获取salary
        else if(type.equals("salary")){
            sum = user.getSalary().multiply(taxRate).subtract(cast);
        }else if (type.equals("commission")){
            BigDecimal commissionRate = user.getCommissionRate();
            sum = user.getSalary();
        }
        //委托
        //获取固定工资
        //计算佣金1
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
