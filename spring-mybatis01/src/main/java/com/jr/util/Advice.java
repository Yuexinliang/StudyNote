package com.jr.util;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * className Advice
 * packageName com.jr.util
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 10:32
 */
@Component
@Aspect
public class Advice {
    @Before ("execution(* com.jr.service.impl.DeptServiceImpl.*Dept(..)) || execution(* com.jr.service.impl.EmpServiceImpl.*Emp(..))")
    public void check(){
        System.out.println("权限检查..");
    }
    @After ("execution(* com.jr.service.impl.*ServiceImpl.*(..))")
    public void log(){
        System.out.println("日志记录");
    }

}
