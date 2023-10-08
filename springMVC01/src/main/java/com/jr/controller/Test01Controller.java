package com.jr.controller;

import com.jr.entity.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * className Test01Controller
 * packageName com.jr.controller
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/07 21:12
 */
@Slf4j
@Controller
public class Test01Controller {
    @Resource
    private ApplicationContext context;

    @RequestMapping ("zcb")
    public ModelAndView test02(String choose) {
        /*作用域为request，一次请求内*/
        ModelAndView mv=new ModelAndView();
        List<Emp> emps = new ArrayList<Emp>();
        Emp emp = context.getBean("emp", Emp.class);
        log.info(emp.toString());
        emp.setEname("ss");
        emp.setEmpno(1);
        emp.setDeptno(10);
        emps.add(emp);
        Emp emp1 = context.getBean("emp", Emp.class);
        emp1.setEname("bb");
        emp1.setEmpno(2);
        emp1.setDeptno(20);
        emps.add(emp1);

        if (choose.equals("2")){
            mv.addObject("emps",emps);
            mv.setViewName("hellow.jsp");
        }
        return mv;
    }
}
