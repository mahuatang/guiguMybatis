package com.atguigu.test.dao;

import com.atguigu.test.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);
    public Employee getEmpAndDept(Integer id);
    public Employee getEmpByIdStep(Integer id);
    public List<Employee> getEmpsByDeptId(Integer deptId);
}
