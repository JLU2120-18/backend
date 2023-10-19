package com.salary.controller;

import com.salary.dao.TimeCardMapper;
import com.salary.form.TimeCardUpdateForm;
import com.salary.pojo.Page;
import com.salary.pojo.TimeCard;
import com.salary.service.TimeCardService;
import com.salary.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * 描述：
 */
@RestController
@RequestMapping("/napi/timecard")
public class TimeCardController {

    @Autowired
    TimeCardService timeCardService;

    @Autowired
    TimeCardMapper timeCardMapper;

    @GetMapping("/get")
    public Page<TimeCard> getTimeCard(@RequestParam("jwt") String jwt,
                                      @RequestParam("pageIndex") Long pageIndex,
                                      @RequestParam("pageSize") Long pageSize){
        //验证权限
        Claims claims = JwtUtils.parseToken(jwt);
        String role = claims.get("role").toString();
        if (!role.equals("employee")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //获取用户id
        String employeeId = claims.get("id").toString();
        return timeCardService.getTimeCard(employeeId,pageIndex,pageSize);

    }

    @GetMapping("/available")
    public Page<TimeCard> getAvailableTimeCard(@RequestParam("jwt") String jwt){
        //验证权限
        Claims claims = JwtUtils.parseToken(jwt);
        String role = claims.get("role").toString();
        if (!role.equals("employee")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //获取用户id
        String employeeId = claims.get("id").toString();
        return timeCardService.getAvailableTimeCard(employeeId);

    }

    @PostMapping("/update")
    public void updateTimeCard(@RequestBody TimeCardUpdateForm form){
        //验证权限
        String jwt = form.getJwt();
        Claims claims = JwtUtils.parseToken(jwt);
        String role = claims.get("role").toString();
        if (!role.equals("employee")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String employeeId = timeCardMapper.selectEmployeeIdByTimeCardId(form.getId());
        String id = claims.get("id").toString();
        //没查到对应的timeCard就报错
        if (employeeId == null || !employeeId.equals(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        timeCardService.updateTimeCard(employeeId,form.getId(),form.getData());
    }

}
