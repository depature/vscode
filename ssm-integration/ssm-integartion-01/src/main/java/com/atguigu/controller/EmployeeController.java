package com.atguigu.controller;

import com.atguigu.pojo.Employee;
import com.atguigu.service.EmployeeService;
import com.atguigu.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("list")
    public List<Employee> findall(){
        List<Employee> all=employeeService.findAll();
        return all;
    }
}
