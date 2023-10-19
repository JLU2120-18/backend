package com.salary.controller;

import com.salary.common.Page;
import com.salary.dto.UserDTO;
import com.salary.pojo.User;
import com.salary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/napi/employee")
public class EmployeeController {
    @Resource
    private UserService userService;

    /**
     * 添加员工
     * @param user
     * @return
     */
    @PostMapping("/create")
    public User create(@RequestBody UserDTO user) {
        return userService.createEmployee(user);
    }

    /**
     * 查询单个员工信息
     * @param jwt
     * @param id
     * @return
     */
    @GetMapping("/get")
    public User get(@RequestParam("jwt") String jwt, @RequestParam(value = "id", required = false) String id) {
        return userService.getEmployee(jwt, id);
    }

    /**
     * 查询以id为前缀的员工信息分页
     * @param jwt
     * @param id
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/gets")
    public Page<User> gets(@RequestParam("jwt") String jwt, @RequestParam(value = "id", required = false) String id, @RequestParam("pageIndex") long pageIndex, @RequestParam("pageSize") long pageSize) {
        return userService.getEmployees(jwt, id, pageIndex, pageSize);
    }

    /**
     * 查询以id为前缀的所有员工id
     * @param id
     * @return
     */
    @GetMapping("/sug")
    public Page<String> suggest(@RequestParam("jwt") String jwt, @RequestParam("id") String id) {
        return userService.suggestIds(jwt, id);
    }

    /**
     * 更新员工信息
     * @param user
     */
    @PostMapping("/update")
    public void update(@RequestBody UserDTO user) {
        userService.updateEmployee(user);
    }

    /**
     * 删除员工
     * @param user
     */
    @PostMapping("/delete")
    public void delete(@RequestBody UserDTO user) {
        userService.deleteEmployee(user);
    }
}
