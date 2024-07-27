package com.atguigu.mapper;

import com.atgugi.project.employee;

public interface EmployeeMapper {
    employee queryById(Integer id);
    int deleteById(Integer id);

    int insertEmp(employee employee);
}
