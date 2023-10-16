package com.salary.service;

import com.salary.dao.AdminReportMapper;
import com.salary.dao.PurchaseMapper;
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

    @Autowired
    PurchaseMapper purchaseMapper;

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
            EmployeeDurationReport employeeDurationReport = new EmployeeDurationReport(
                employeeId,name,startTime,endTime,duration
            );
            EmployeeDurationReport[] employeeDurationReports = {employeeDurationReport};
//            employeeDurationReports[0] = employeeDurationReport;
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
        //计算除去税收百分比
        BigDecimal taxRate = BigDecimal.valueOf(1).subtract(user.getTaxRate());
        //其他减免
        BigDecimal cast = user.getOtherCast();
        //时薪
        switch (type) {
            case "wage":
                //获取考勤卡，要注意是否可以加班以及加班工资计算
                List<TimeCard> timecards = adminReportMapper.selectTimeCard(employeeId);
                for (TimeCard timecard : timecards) {
                    BigDecimal x = new BigDecimal(8);
                    BigDecimal duration = timecard.getDuration();
                    BigDecimal hourWage = user.getSalary();
                    if (duration.compareTo(x) < 0) {
                        sum = hourWage.multiply(duration).multiply(taxRate).subtract(cast);
                    } else {
                        int limit = user.getDurationLimit();
                        if (limit != 0) {
                            sum = hourWage.multiply(x).multiply(taxRate).subtract(cast);
                        } else {
                            BigDecimal cnt = duration.subtract(x);
                            sum = hourWage.multiply(x).
                                    add(hourWage.multiply(BigDecimal.valueOf(1.5)).
                                            multiply(cnt)).multiply(taxRate).subtract(cast);
                        }
                    }
                }
                break;
            case "salary":          //受雇
                //固定工资直接获取salary
                sum = user.getSalary().multiply(taxRate).subtract(cast);
                break;
            case "commission":    //委托
                BigDecimal commissionRate = user.getCommissionRate();
                //计算采购订单总金额
                List<BigDecimal> pays = purchaseMapper.selectAllPay(employeeId);
                BigDecimal payment = new BigDecimal(0);
                for (BigDecimal pay : pays) {
                    payment = payment.add(pay);
                }
                //计算佣金
                payment = payment.multiply(commissionRate);
                //计算总薪资
                sum = user.getSalary().add(payment).multiply(taxRate).subtract(cast);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        EmployeeSalaryReport employeeSalaryReport = new EmployeeSalaryReport(
                employeeId,name,startTime,endTime,sum
        );
        EmployeeSalaryReport[] employeeSalaryReports = {employeeSalaryReport};
        return new AdminReportVO<>(employeeSalaryReports);
    }
}
