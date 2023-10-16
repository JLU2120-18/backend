package com.salary.controller;

import com.salary.form.AdminReportForm;
import com.salary.service.AdminReportService;
import com.salary.vo.AdminReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * 描述：薪资管理员controller层
 */
@RestController
@RequestMapping("/admin_report")
public class AdminReportController {

    @Autowired
    AdminReportService adminReportService;
    @PostMapping("/create")
    public AdminReportVO create(@RequestBody AdminReportForm form){
        String type = form.getType();
        String startTime = form.getStartTime();
        String endTime = form.getEndTime();
        String employeeId = form.getEmployeeId();
        if (type.equals("duration")){
            return adminReportService.createDuration(startTime,endTime,employeeId);
        }else if(type.equals("salary")){
//            return AdminReportVO.error();
            return adminReportService.createSalary(startTime,endTime,employeeId);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
