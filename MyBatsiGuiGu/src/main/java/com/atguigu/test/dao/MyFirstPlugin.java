package com.atguigu.test.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

@Intercepts(
        {
                @Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
        })
public class MyFirstPlugin implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirst Plugin Interceptor ...");

        Object target = invocation.getTarget();

        System.out.println("当前拦截到的对象：" + target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是："+value);
        metaObject.setValue("parameterHandler.parameterObject", 11);
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象"+target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件注册时的属性：" + properties);
    }
}
