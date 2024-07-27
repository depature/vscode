package com.atguigu.mapper;

import com.atguigu.pojo.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    List<Employee> queryAll();
}
