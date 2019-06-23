package com.atguigu.test.dao;

import com.atguigu.test.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    public List<Employee> getEmpsTestInnerParamter(Employee employee);

    public List<Employee> getEmpsByConitionIf(Employee employee);

    public List<Employee> getEmpsByConitionTrim(Employee employee);

    public List<Employee> getEmpsByConitionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConitionForeach(@Param("ids")List<Integer> ids);

    public void addEmps(@Param("emps")List<Employee> emps);
}
