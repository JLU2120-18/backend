package com.salary.controller;

import com.salary.form.AdminReportForm;
import com.salary.service.AdminReportService;
import com.salary.utils.JwtUtils;
import com.salary.vo.ReportVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述：薪资管理员controller层
 */
@RestController
@RequestMapping("/api/admin_report")
public class AdminReportController {

    @Autowired
    AdminReportService adminReportService;
    @PostMapping("/create")
    public ReportVO create(@RequestBody AdminReportForm form){
        String jwt = form.getJwt();
        Claims claims = JwtUtils.parseToken(jwt);
        if (!claims.get("role").toString().equals("payroll")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String type = form.getType();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();
        String employeeId = form.getEmployeeId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endTime, formatter);
        // 计算差值
        Duration duration = Duration.between(startDate, endDate);
        long days = duration.toDays();
        if (days < 0 || employeeId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (type.equals("duration")){
            return adminReportService.createDuration(startTime,endTime,employeeId);
        }else if(type.equals("salary")){
            return adminReportService.createSalary(startTime,endTime,employeeId);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}