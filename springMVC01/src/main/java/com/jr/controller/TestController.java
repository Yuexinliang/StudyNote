package com.jr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * className TestController
 * packageName com.jr.controller
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/07 14:29
 */
@Controller
public class TestController {
    @RequestMapping ("login")
    public ModelAndView test01(String uname,String pwd) {
        /*作用域为request，一次请求内*/
        ModelAndView mv=new ModelAndView();
        if (uname.equals("张三") && pwd.equals("123")){
            mv.addObject("info",uname+"登录成功");
            /*进行的是请求转发*/
            mv.setViewName("ok.jsp");
        }else {
            mv.setViewName("index.jsp");
        }
        return mv;
    }
}
