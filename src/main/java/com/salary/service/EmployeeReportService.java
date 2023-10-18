package com.salary.service;

import com.salary.dao.PurchaseMapper;
import com.salary.dao.TimeCardMapper;
import com.salary.dao.UserMapper;
import com.salary.pojo.TimeCard;
import com.salary.pojo.TimeCardProject;
import com.salary.pojo.TimeCardProjectData;
import com.salary.pojo.User;
import com.salary.reports.DurationReport;
import com.salary.reports.ProjectDurationReport;
import com.salary.reports.SalaryReport;
import com.salary.reports.VacationReport;
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

/**
 * 描述：
 */
@Service
public class EmployeeReportService {
    @Autowired
    TimeCardMapper timeCardMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PurchaseMapper purchaseMapper;

    /*
    * 创建总工作时数报告
    * */
    public ReportVO<DurationReport[]> createDuration(String startTime, String endTime, String employeeId) {
        //传入id为null，创建全部，否则指定
        if (employeeId == null){
//            return AdminReportVO.error();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            //查询考勤卡，计算总时长
            List<TimeCard> timecards = timeCardMapper.selectTimeCardsById(employeeId,startTime,endTime);
            BigDecimal duration = new BigDecimal(0);
            for(TimeCard timecard : timecards){
                duration = duration.add(timecard.getDuration());
            }
            //查询员工名字
            String employeeName = userMapper.selectNameById(employeeId);
//            System.out.println(name);
            if (employeeName == null)
//                return AdminReportVO.error();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            //封装信息返回
            DurationReport durationReport = new DurationReport(
                    employeeId,employeeName,startTime,endTime,duration
            );
            DurationReport[] durationReports = {durationReport};
//            employeeDurationReports[0] = employeeDurationReport;
            return ReportVO.success(durationReports);
        }
    }



    /**
     *创建项目总工作时数报告
     */
    public ReportVO<ProjectDurationReport[]> createProjDuration(String timeCardId,String startTime,String endTime,String employeeId){
        if (timeCardId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        //根据id查找用户名
        String employeeName = userMapper.selectNameById(employeeId);
        if (employeeName == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        //根据timeCardId查找timeCardProject
        List<TimeCardProject> projects = timeCardMapper.selectProjByTimeCardId(timeCardId);
        //封装数组
        TimeCardProjectData[] data = new TimeCardProjectData[projects.size()];
        int i = 0;
        for (TimeCardProject project : projects){
            data[i].setProjectName(project.getProjectName());
            data[i].setDuration(project.getDuration());
        }
        //封装报告信息
        ProjectDurationReport projectDurationReport = new ProjectDurationReport(
                employeeId,employeeName,startTime,endTime,data
        );
        ProjectDurationReport[] projectDurationReports = {projectDurationReport};
        return ReportVO.success(projectDurationReports);
    }



    /*
    * 创建假期/病假报告
    * */
    public ReportVO<VacationReport> createVacation(String startTime, String endTime, String employeeId){
        //根据用户id查询考勤卡
        List<TimeCard> timeCards =timeCardMapper.selectTimeCardsById(employeeId,startTime,endTime);
        //计算天数
        int days = DateUtils.getDays(timeCards);
        //
        return  null;
    }


    /*
    * 创建总工资报告
    * */
    public ReportVO<SalaryReport[]> createSalary(String startTime, String endTime, String employeeId) {
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //当前日期
        String d = sdf.format(date);
        //求月份用于计算薪资
        BigDecimal months = BigDecimal.valueOf(DateUtils.getMonths(d) - 1);


        //时薪
        switch (type) {
            case "wage":
                //年初日期
                String start = DateUtils.getYearInit(d);
                //获取考勤卡，要注意是否可以加班以及加班工资计算
                List<TimeCard> timecards = timeCardMapper.selectTimeCardsById(employeeId,start,d);
                for (TimeCard timecard : timecards) {
                    BigDecimal x = new BigDecimal(8);
                    BigDecimal duration = timecard.getDuration();
                    BigDecimal hourWage = user.getHourWage();
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
                sum = user.getSalary().multiply(months).multiply(taxRate).subtract(cast);
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
                sum = user.getSalary().multiply(months).add(payment).multiply(taxRate).subtract(cast);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        SalaryReport salaryReport = new SalaryReport(
                employeeId,name,startTime,endTime,sum
        );
        SalaryReport[] salaryReports = {salaryReport};
        return ReportVO.success(salaryReports);
    }
}
