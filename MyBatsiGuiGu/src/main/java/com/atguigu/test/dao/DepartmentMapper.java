package com.atguigu.test.dao;

import com.atguigu.test.bean.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);
    public Department getDeptByIdPlus(Integer id);
    public Department getDeptByIdStep(Integer id);
}
