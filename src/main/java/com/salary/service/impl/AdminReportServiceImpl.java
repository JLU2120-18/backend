package com.salary.service.impl;

import com.salary.dao.PurchaseMapper;
import com.salary.dao.TimeCardMapper;
import com.salary.dao.UserMapper;
import com.salary.pojo.TimeCard;
import com.salary.pojo.User;
import com.salary.reports.EmployeeDurationReport;
import com.salary.reports.EmployeeSalaryReport;
import com.salary.service.AdminReportService;
import com.salary.utils.DateUtils;
import com.salary.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminReportServiceImpl implements AdminReportService {

    @Autowired
    TimeCardMapper timeCardMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PurchaseMapper purchaseMapper;


    /*
    * 创建指定时间内总工作时数报告
    * */
    @Override
    public ReportVO<EmployeeDurationReport[]> createDuration(String startTime, String endTime, String employeeId) {
        //传入id为null，创建全部，否则指定
//        if (employeeId == null){
////            return AdminReportVO.error();
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }else{
            //查询时间内考勤卡，计算总时长
            List<TimeCard> timecards = timeCardMapper.selectTimeCardsByEmployeeId(employeeId,startTime,endTime);
            BigDecimal duration = new BigDecimal(0);
            for(TimeCard timecard : timecards){
                duration = duration.add(timecard.getDuration());
            }
            //查询员工名字
            String name = userMapper.selectNameById(employeeId);
            if (name == null)
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            //封装信息返回
            EmployeeDurationReport employeeDurationReport = new EmployeeDurationReport(
                employeeId,name,startTime,endTime,duration
            );
            EmployeeDurationReport[] employeeDurationReports = {employeeDurationReport};
            return ReportVO.success(employeeDurationReports);
//        }
    }



    /*
    * 创建年度至今薪资报告
    * */
    @Override
    public ReportVO<EmployeeSalaryReport[]> createSalary(String startTime, String endTime, String employeeId) {
        //获取User
        User user = userMapper.selectUserById(employeeId);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        //获取name
        String name = user.getUsername();
        //根据员工类型计算salary
        String type = user.getType();
        BigDecimal sum;
        //计算除去税收百分比
        BigDecimal taxRate = BigDecimal.valueOf(1).subtract(user.getTaxRate());
        //其他减免
        BigDecimal cast = user.getOtherCast();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //当前日期
        String d = sdf.format(date);
        BigDecimal months = BigDecimal.valueOf(DateUtils.getMonths(d) - 1);

        //时薪
        switch (type) {
            //时薪
            case "wage":
                //年初日期
                String start = DateUtils.getYearInit(d);
                //获取时薪
                BigDecimal hourWage = user.getHourWage();
                BigDecimal duration = BigDecimal.valueOf(0);
                //获取用户最大工时限制
                BigDecimal durationLimit = BigDecimal.valueOf(user.getDurationLimit());
                //基础工时
                BigDecimal baseuration = BigDecimal.valueOf(40);
                List<TimeCard> timecards = timeCardMapper.selectTimeCardsByEmployeeId(employeeId,start,d);
                for (TimeCard timecard : timecards) {
                    duration = duration.add(timecard.getDuration());
                }
                if (duration.compareTo(baseuration) < 0) {
                    sum = hourWage.multiply(duration).subtract(cast).multiply(taxRate);
                } else {
                    if (duration.compareTo(durationLimit) > 0) {
                        duration = durationLimit;
                    }
                    BigDecimal cnt = duration.subtract(baseuration);
                    sum = hourWage.multiply(baseuration).
                            add(hourWage.multiply(BigDecimal.valueOf(1.5)).
                                    multiply(cnt)).subtract(cast).multiply(taxRate);
                }
                break;
            case "salary":          //受雇
                //固定工资直接获取salary
                sum = user.getSalary().multiply(months).subtract(cast).multiply(taxRate);
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
                sum = user.getSalary().multiply(months).add(payment).subtract(cast).multiply(taxRate);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        EmployeeSalaryReport employeeSalaryReport = new EmployeeSalaryReport(
                employeeId,name,startTime,endTime,sum
        );
        EmployeeSalaryReport[] employeeSalaryReports = {employeeSalaryReport};
        return ReportVO.success(employeeSalaryReports);
    }
}
