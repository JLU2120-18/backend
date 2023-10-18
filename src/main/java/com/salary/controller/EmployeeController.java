package com.salary.controller;

import com.salary.common.Page;
import com.salary.pojo.User;
import com.salary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jialin
 * @create 2023-10-16 12:55
 */

@Slf4j
@RestController
@RequestMapping("/api/employee")
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

    /**
     * 获取单个员工信息
     * @param id
     * @param jwt
     * @return
     */
    @GetMapping("/get")
    public User get(@RequestParam String id, @RequestParam String jwt) {
        return userService.getEmployee(id, jwt);
    }

    /**
     * 获取以id为前缀的员工信息分页
     * @param jwt
     * @param id
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/gets")
    public Page<User> gets(@RequestParam String jwt, @RequestParam String id, @RequestParam long pageIndex, @RequestParam long pageSize) {
        return userService.getEmployees(jwt, id, pageIndex, pageSize);
    }

    /**
     * 获取以id为前缀的所有员工id
     * @param id
     * @return
     */
    @GetMapping("/sug")
    public Page<String> suggest(@RequestParam String id) {
        return userService.suggestIds(id);
    }

    /**
     * 更新员工信息
     * @param user
     */
    @PostMapping("/update")
    public void update(@RequestBody User user) {
        userService.updateEmployee(user);
    }

    /**
     * 删除员工
     * @param user
     */
    @PostMapping("/delete")
    public void delete(@RequestBody User user) {
        userService.deleteEmployee(user);
    }
}
