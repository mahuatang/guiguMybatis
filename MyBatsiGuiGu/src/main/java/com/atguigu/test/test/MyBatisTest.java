package com.atguigu.test.test;

import com.atguigu.test.bean.Department;
import com.atguigu.test.bean.Employee;
import com.atguigu.test.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MyBatisTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testBatchSave() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperDynamicSQL employeeMapperDynamicSQL = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            List<Employee> list = new ArrayList<>();

            list.add(new Employee(null, "smith0x1", "smith0x1@atguigu.com", "1",new Department(1)));
            list.add(new Employee(null, "allen0x1", "allen0x1@atguigu.com", "0",new Department(1)));
            employeeMapperDynamicSQL.addEmps(list);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDynamicSql() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);

            Employee employee = new Employee(3, "zhanglong", "email@163.com", "0", null);
            List<Employee> list = mapper.getEmpsByConitionIf(employee);
            for (Employee emp : list) {
                System.out.println(emp);
            }

            /*List<Employee> list2 = mapper.getEmpsByConitionTrim(employee);
            for (Employee emp : list2) {
                System.out.println(emp);
            }*/

            /*List<Employee> list3 = mapper.getEmpsByConitionChoose(employee);
            for (Employee emp : list3) {
                System.out.println(emp);
            }*/

           /* mapper.updateEmp(employee);
            sqlSession.commit();*/
/*
            List<Employee> list = mapper.getEmpsByConitionForeach(Arrays.asList(1,2));
            for (Employee emp : list) {
                System.out.println(emp);
            }*/


        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            Employee employee = sqlSession.selectOne("com.atguigu.test.dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test03() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            /*Employee employee = new Employee(null, "jerry4", "@163.com", "1");
            mapper.addEmp(employee);
            System.out.println(employee.getId());*/

            /*Employee employee = new Employee(1, "Tom", "jerry@atguigu.com", "0");
            boolean updateEmp = mapper.updateEmp(employee);
            System.out.println(updateEmp);*/

            mapper.deleteEmpById(2);

            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test04() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        //    Employee employee = employeeMapper.getEmpByIdAndLastName(1, "Tom");
            /*Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "Tom");
            map.put("tableName", "tbl_employee");
            Employee employee = employeeMapper.getEmpByMap(map);
            System.out.println(employee);*/

            /*List<Employee> like = employeeMapper.getEmpsByLastNameLike("%j%");
            for (Employee emp : like) {
                System.out.println(emp);
            }*/


            Map<String,Employee> map = employeeMapper.getEmpByIdReturnMap(1);
            System.out.println(map);
            System.out.println(map.entrySet().iterator().next().getKey());

            Map<String, Employee> map2 = employeeMapper.getEmpByLastNameLikeReturnMap("%o%");
            System.out.println(map2);
        }finally {
            session.close();
        }
    }

    @Test
    public void test05() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);

            /*Employee employee = mapper.getEmpById(1);
            System.out.println(employee);*/

           /* Employee empAndDept = mapper.getEmpAndDept(1);
            System.out.println(empAndDept);
            System.out.println(empAndDept.getDept());*/

            Employee employee = mapper.getEmpByIdStep(1);
            System.out.println(employee);
            System.out.println(employee.getDept());
        }finally {
            session.close();
        }
    }

    @Test
    public void test06() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            /*Department department = mapper.getDeptByIdPlus(1);
            System.out.println(department.getEmps());*/
            Department deptByIdStep = mapper.getDeptByIdStep(1);
            System.out.println(deptByIdStep.getDepartmentName());
            System.out.println(deptByIdStep.getEmps());
        } finally {
            session.close();
        }
    }





































}
