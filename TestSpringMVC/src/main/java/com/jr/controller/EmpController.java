package com.jr.controller;

import com.jr.entity.Dept;
import com.jr.entity.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * className EmpController
 * packageName com.jr.controller
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/08 08:37
 */
@Controller
/*
 * 如果类定义处添加这个注解，那么该类下所有方法都会自动拼接一个注解路径
 * 例下方会被拼接成emp/login
 * 控制器里的方法，在作页面跳转时，全从一级路由下进行跳转jsp,也就是会在emp这个逻辑文件夹下寻找jsp页面进行跳转
 * 所以需要使用/ .jsp来进行项目根目录jsp页面寻找
 * */
@RequestMapping("emp")
@Slf4j
public class EmpController {
    /*@RequestMapping(value="/login")
    * 如果@RequestMapping中只有一个value属性，且该value只有1个值时可以value省略不写
    * @RequestMapping(value={"login"，”reg“})
	===表示两个url请求都可以进入此控制器的此方法里。
    ===如果value的值只有一个的话，不需要加{}
    * @RequestMapping(value={"show.do"，”reg.do“} ，method={RequestMethod.POST})
	===value  和method两个属性之间使用 逗号，隔开
    ===method={RequestMethod.POST} 表示当前方法只能处理POST请求，若缺省，则get/post都可以处理。
    * */
    @RequestMapping(value={"/login","/reg"})
    /*如果页面传入参数类型与形参类型不一致会出404*/
    public ModelAndView login(Integer empno,@RequestParam(value = "ename",defaultValue = "admin") String name){
        ModelAndView mv = new ModelAndView();
        if (empno.equals(123) && name.equals("张三")){
            mv.addObject("info",name+"登录成功");
            /*进行的是请求转发*/
            mv.setViewName("/ok");/*转发的/定位到项目根目录*/
        }else {
            mv.addObject("info",name+"登录失败");
            mv.setViewName("/index");
        }
        return mv;
    }
    @RequestMapping("/ts1")
    public String test01(Integer empno,String name,Model model, HttpSession session){
        /*单独使用model进行返回值放入*/
//        model.addAttribute("info","String返回值形式");
//        return "/ok";

        /*以下方式写转发与重定向不会进入视图解析器*/
//        return "forward:/ok.jsp";
        /*重定向无法使用model进行值传递，会拼接到地址栏中*/
        /*该重定向不会从服务器根目录查找，而是从项目根目录*/
        session.setAttribute("info","String重定向返回值形式");
        return "redirect:/ok.jsp";
    }
    @RequestMapping("/ts2")
    public void test02(HttpServletResponse response,HttpSession session) throws IOException {
        session.setAttribute("info","String返回值形式");
        /*会从服务器根目录查找，所以要拼接tomcat上配置的项目名，也就是应用程序上下文*/
        response.sendRedirect("/ok.jsp");
    }
    /*接受entity实体类*/
    @RequestMapping("/ts3")
    public ModelAndView test03(Emp emp){
        ModelAndView mv = new ModelAndView();
        if (emp.getEname().equals("张三") && emp.getEmpno().equals(123)){
            mv.addObject("info",emp.getEname()+emp.getSal()+"登录成功且"+emp.getIsLive());
            mv.setViewName("/ok");/*转发的/定位到项目根目录*/
        }
        return mv;
    }
    /*接受包装类实现类，存在类依赖*/
    @RequestMapping("/ts4")
    public ModelAndView test04(Emp emp){
        ModelAndView mv = new ModelAndView();
        log.info(emp.toString());
        mv.addObject("emp",emp);
        mv.setViewName("/show");/*转发的/定位到项目根目录*/
        return mv;
    }
    /*登录进入show*/
    @RequestMapping("/ts5")
    public String test05(Emp emp){
        log.info("ts5");
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("show");
//        return mv;

        /*使用mv.setViewName("show");进行转发要经过视图解析器，会拼接上.jsp，进不到方法中，所以专用String的转发或者重定向*/
        /*转发和重定向不写/时，是从当前上级目录下开始寻找资源*/

        /*进入list集合获取值方法*/
//        return "forward:show";
//        return "redirect:show";

        /*进入map的值获取方法*/
        return "forward:mapshow";
    }
    /*获取集合进行展示*/
    @RequestMapping("/show")
    public String show(HttpSession session){
        List<Dept> depts = new ArrayList<Dept>();
        depts.add(new Dept(10,"aaa","aaa"));
        depts.add(new Dept(20,"bbb","bbb"));
        depts.add(new Dept(30,"ccc","ccc"));
        depts.add(new Dept(40,"ddd","ddd"));
        session.setAttribute("list",depts);
        /*重定向不经过视图解析器，要写全.jsp，这里重定向是为了从二级路径脱离出来*/
        return "redirect:/ok.jsp";
    }

    /*获取map集合进行展示*/
    @RequestMapping("/mapshow")
    public String mapshow(HttpSession session){
        Map<Integer,Dept> deptMaps = new HashMap<Integer, Dept>();
        Dept dept1 = new Dept(10,"aaa","aaa");
        Dept dept2 = new Dept(20,"bbb","bbb");
        Dept dept3 = new Dept(30,"ccc","ccc");
        Dept dept4 = new Dept(40,"ddd","ddd");

        deptMaps.put(dept1.getDeptno(),dept1);
        deptMaps.put(dept2.getDeptno(),dept2);
        deptMaps.put(dept3.getDeptno(),dept3);
        deptMaps.put(dept4.getDeptno(),dept4);
        session.setAttribute("maps",deptMaps);
        /*重定向不经过视图解析器，要写全.jsp，这里重定向是为了从二级路径脱离出来*/
        return "redirect:/ok.jsp";
    }

    /*批量修改*/
    @RequestMapping("/update")
    public ModelAndView update(Emp emp){
        ModelAndView mv = new ModelAndView();
        mv.addObject("emp",emp);
        mv.setViewName("/show");
        return mv;
    }
}
