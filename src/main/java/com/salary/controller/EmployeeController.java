package com.salary.controller;

import com.salary.pojo.User;
import com.salary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Jialin
 * @create 2023-10-16 12:55
 */

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private UserService userService;

    /**
     * 创建员工
     * @param user
     * @return
     */
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.createEmployee(user);
    }
}
