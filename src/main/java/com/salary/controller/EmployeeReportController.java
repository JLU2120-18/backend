package com.salary.controller;

import com.salary.form.EmployeeReportForm;
import com.salary.service.EmployeeReportService;
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
 * 描述：
 */
@RestController
@RequestMapping("/api/employee_report")
public class EmployeeReportController {

    @Autowired
    EmployeeReportService employeeReportService;

    @PostMapping("/create")
    public ReportVO create(@RequestBody EmployeeReportForm form){
        String jwt = form.getJwt();
        Claims claims = JwtUtils.parseToken(jwt);
        String role = claims.get("role").toString();
        if (!role.equals("employee") && !role.equals("commission")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String employeeId = claims.get("id").toString();
        String type = form.getType();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endTime, formatter);
        // 计算差值
        Duration duration = Duration.between(startDate, endDate);
        long days = duration.toDays();
        if (days < 0 || employeeId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        switch (type) {
            case "duration":
                return employeeReportService.createDuration(startTime, endTime, employeeId);
            case "proj_duration":
                String timeCardId = form.getTimeCardId();
                return employeeReportService.createProjDuration(timeCardId, startTime, endTime, employeeId);
            case "vacation":
                return employeeReportService.createSalary(startTime, endTime, employeeId);
            case "salary":
                return employeeReportService.createVacation(startTime, endTime, employeeId);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
