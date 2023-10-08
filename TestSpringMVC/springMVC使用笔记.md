# springMVC使用笔记
## 配置springMVC.xml文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
	   http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/context
	   http://www.springframework.org/schema/context/spring-context-4.3.xsd">
    
    <!--配置映射器和适配器：选择默认-->
    <!-- 设置注解扫描路径 -->
    <context:component-scan base-package="com.jr"/>

    <!-- 默认加载注解处理器映射器和处理器适配器及很多的参数绑定 ,可以配置自定义类型适配器-->
    <mvc:annotation-driven/>

</beans>
```
## 配置web.xml文件
```xml
<?xml version="1.0" encoding="UTF-8"?>

<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                      https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
         version="5.0"
         metadata-complete="false">
  <display-name>Archetype Created Web Application</display-name>

  <servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!--    init-param 告诉ServletSpringMVC配置文件在哪里 -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springMVC.xml</param-value>
    </init-param>
    <!-- 自启动-在启动tomcat时立即加载对应的Servlet -->
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <!--
    /*：所有请求都进前端控制器，包括jsp也需要前往映射器
    /：的意思是：除了JSP放行,其他全按照名称去找@RequestMapping() -->
    <url-pattern>/</url-pattern>
  </servlet-mapping>

  <!-- 配置过滤器：用来设置字符编码，防止乱码！ -->
  <filter>
    <filter-name>charset</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>charset</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
</web-app>
```
配置视图解析器
```xml
    <!--配置视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--配置资源路径，直接在webapp下就不需要写路径-->
        <property name="prefix" value="" />
        <!--配置这个后在请求资源时忽略.jsp后缀-->
        <property name="suffix" value=".jsp" />
    </bean>
```
## servlet
>@RequestMapping()标注类和@RequestMapping()标注方法形成二级路径，因为是使用的二级路径所以以下转发或重定向要使用 / 去除一级路径后找寻资源<br><br>
> 参数绑定
RequestParam(value = "ename",defaultValue = "admin")String name
当前端传出来的参数名与形参名不一致时，使用value绑定参数名，当同名参数名为空时，使用defaultValue添加默认值。
```java
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

@Controller
/*
 * 如果类定义处添加这个注解，那么该类下所有方法都会自动拼接一个注解路径
 * 例下方会被拼接成emp/login
 * 控制器里的方法，在作页面跳转时，全从一级路由下进行跳转jsp,也就是会在emp这个逻辑文件夹下寻找jsp页面进行跳转
 * 所以需要使用/ .jsp来进行项目根目录jsp页面寻找
 * 
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
}
```
## springMVC返回值类型
>controller 方法的返回值有三种：<br>
A.ModelAndView<br>
B.String ---视图，数据由参数中的Model对象存储并返回<br>
C.void

使用ModelAndView既可添加model数据也可以指定view。
```java
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
```
返回String，只进行页面跳转，不带值
>方法返回值为String时，字符串内容不是随意的，而是要遵守一定的格式！有三种方式：<br><br>
A.字符串内容==>表示逻辑视图名，以请求转发方式进行页面跳转，也可以是controller控制器。<br><br>
如：return  “index.jsp”;<br><br>
B.字符串内容==>以“forward：”开头，表示以请求转发方式进行页面跳转，也可以是controller控制器。<br><br>
如：return  “forward：index.jsp”;<br><br>
C.字符串内容==>以“redirect：”开头，表示以重定向方式进行页面跳转，跳转的路径可以是jsp页面，也可以是controller控制器。<br><br>
如：return  “redirect：index.jsp”;<br><br>
注意： 如果applicationContext.xml（spring）里配置了视图解析器，该方法(指forward和redirect)不进入【视图解析器】的。

```java
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
```
返回void，只进行页面跳转，不带值
>方法返回值为void时，有以下两种方式：<br><br>
A. request.getRequestDispatcher("main.action").forward(request, response);表示请求转发方式进行页面跳转<br><br>
B. response.sendRedirect("main.action");表示重定向方式进行页面跳转
```java
@RequestMapping("/ts2")
public void test02(HttpServletResponse response,HttpSession session) throws IOException {
    session.setAttribute("info","String返回值形式");
    /*会从服务器根目录查找，所以要拼接tomcat上配置的项目名，也就是应用程序上下文，但这里我的应用程序上下文只有/，所以就不用拼接了*/
    response.sendRedirect("/ok.jsp");
}
```
## POJO类型绑定
定义时间类型数据适配器类
```java
package com.jr.util;
import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class MyDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String str) {
        Date dt=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt=sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }
}
```
springMVC.xml配置时间类型数据自定义适配器
```xml
    <!-- 默认加载注解处理器映射器和处理器适配器及很多的参数绑定 ,可以配置自定义类型适配器-->
    <!--将之前的补充上conversion-service="formatc"对应找到适配器-->
    <mvc:annotation-driven conversion-service="formatc"/>

    <!-- 配置类型适配器，这个匹配的是自定义的时间适配器 -->
    <bean id="formatc" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
        <property name="converters">
            <list>
                <bean class="com.jr.util.MyDateConverter"/>
            </list>
        </property>
    </bean>
```
Emp实体类
```java
package com.jr.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
    private Boolean isLive;
    /*包装类型*/
    private Dept dept;
    /*数组*/
    private String[] likes;
    /*list集合*/
    private List<Dept> depts;
    /*map集合*/
    private Map<Integer,Dept> deptMaps;
}
```
```java
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

        /*使用mv.setViewName("show");进行转发要经过视图解析器，会拼接上.jsp，进不到servlet的方法中，所以改用String的转发或者重定向*/
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

    /*批量修改,将修改后的list集合或map集合存储进Emp对象内进行展示*/
    @RequestMapping("/update")
    public ModelAndView update(Emp emp){
        ModelAndView mv = new ModelAndView();
        mv.addObject("emp",emp);
        mv.setViewName("/show");
        return mv;
    }
```
前端jsp页面传输请求信息
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
${info}
    <form action="emp/ts1" method="post">
        员工编号：<input type="text" name="empno"><br/>
        员工姓名：<input type="text" name="ename"/><br/>
        <input type="submit" value="提交">
    </form>

<h1>实体类发送信息</h1>

    <form action="emp/ts3" method="post">
        员工编号：<input type="text" name="empno"><br/>
        员工姓名：<input type="text" name="ename"/><br/>
        工资：<input type="text" name="sal"/><br/>
        是否存活：<input type="radio" name="isLive"  value="true"/>存活
            <input type="radio" name="isLive"  value="false"/>死亡
        <input type="submit" value="提交">
    </form>

<h1>存在依赖的实体类发送信息</h1>

<form action="emp/ts4" method="post">
    员工编号：<input type="text" name="empno"><br/>
    员工姓名：<input type="text" name="ename"/><br/>
    工资：<input type="text" name="sal"/><br/>
    是否存活：<input type="radio" name="isLive"  value="true"/>存活
    <input type="radio" name="isLive"  value="false"/>死亡<br/>
    部门名称：<input type="text" name="dept.dname"/><br/>
    部门位置：<input type="text" name="dept.loc"/><br/>
    <!--数组数据传送-->
    你最喜欢的爱好是：<input type="checkbox" name="likes" value="book"/>图书<br/>
    <input type="checkbox" name="likes" value="food"/>美食<br/>
    <input type="checkbox" name="likes" value="sport"/>运动<br/>
    <%--时间类数据需要额外配置自定义适配器--%>
    入职时间：<input type="date" name="hiredate"/>
    <input type="submit" value="提交">
</form>

<h1>登录后获取集合进行展示</h1>

<form action="emp/ts5" method="post">
    <input type="submit" value="登录">
</form>
</body>
</html>
```
特殊的是list集合和map集合的传输方法
```html
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/10/07
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${info}
<form action="emp/update" method="get">
    <c:forEach items="${list}" var="dept" varStatus="status">
        <table>
            <tr>
                <td>
<!--                    <%&#45;&#45;通过name寻找集合中的每个元素并赋值,depts对应Emp类中的list集合属性名，通过[${status.index}]找到对应索引位置的集合数据&#45;&#45;%>-->
                    部门编号：<input type="text" name="depts[${status.index}].deptno" value="${dept.deptno}"><br/>
                </td>
                <td>
                    部门名称：<input type="text" name="depts[${status.index}].dname" value="${dept.dname}"><br/>
                </td>
                <td>
                    部门地址<input type="text" name="depts[${status.index}].loc" value="${dept.loc}"><br/>
                </td>
            </tr>
        </table>
    </c:forEach>
    <input type="submit" value="批量修改">
</form>

<form action="emp/update" method="get">
    <c:forEach items="${maps}" var="map" varStatus="status">
        <table>
            <tr>
                <td>
<!--                        <%&#45;&#45;deptMaps[${map.value.deptno}]中${map.value.deptno}为key值，这应该是由于springMVC的映射特性，-->
<!--                        使得map集合可以用[key值]来找寻对应数据-->
<!--                        由于使用deptno来作为map的key值，所以也可用map.value.deptno来代表key值&#45;&#45;%>-->
                    部门编号：<input type="text" name="deptMaps[${map.key}].deptno" value="${map.value.deptno}"><br/>
                </td>
                <td>
                    部门名称：<input type="text" name="deptMaps[${map.key}].dname" value="${map.value.dname}"><br/>
                </td>
                <td>
                    部门地址<input type="text" name="deptMaps[${map.value.deptno}].loc" value="${map.value.loc}"><br/>
                </td>
            </tr>
        </table>
    </c:forEach>
    <input type="submit" value="map批量修改">
</form>
</body>
<script src="js/jquery.min.js"></script>
</html>
```
## 拦截器handlers
>编写拦截器类,需要实现HandlerInterceptor接口并重写三个方法
```java
package com.jr.handlers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class MyHandler implements HandlerInterceptor {
    @Override
    /*进入控制器方法前，要执行的拦截器方法。。。。。
    * 返回值：返回值为true----说明，符合通过条件，可以进行进入到控制器里。
    返回值为 false----说明：不符合通过条件，拦截此次请求。  不在继续进入控制器。*/
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("进入拦截器");
        log.info(httpServletRequest.getRequestURL().toString());
        log.info(httpServletRequest.getRequestURI());
        return true;
    }

    @Override
    /*请求已经进入到控制器了，但是还没有执行完控制器的时候，回到拦截器里来，执行该方法。*/
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("视图渲染前");
    }

    @Override
    /*执行完控制器的方法后，执行的拦截器方法。。。。*/
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("视图渲染后");
    }
}
```
## 在springMVC.xml中配置拦截器
>使用<mvc:mapping path="/**"/>配置进入拦截器的路径，**表示包含一二级路径的所有路径都进入拦截器。

> 使用<mvc:exclude-mapping path="/emp/mapshow"/>排除掉该路径，使其不进入拦截器直接获取资源

> 可以使用<<mvc:interceptor>></mvc:interceptor>添加第二个拦截器<br><br>
如果有多个拦截器，则执行方式是链式的，且preHandle方法的执行顺序和剩下两个方法执行顺序相反。 比如 现在有三个拦截器，第一个拦截器preHandle中输出 "pre1" 字样，第二个拦截器preHandle中输出 "pre2" 字样，以此类推。在IoC容器中配置完后，运行程序，得到的结果会是： pre1, pre2, pre3, post3, post2, post1, after3, after2, after1
```xml
    <!-- 配置拦截器 -->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>  <!-- /表示项目根目录；** 项目下任意路径以及任意子路径； 拦截所有请求路径 -->
<!--            <mvc:exclude-mapping path="/emp/ts5"/>-->
            <!--排除后就不会进入拦截器中了-->
            <mvc:exclude-mapping path="/emp/mapshow"/>
            <bean class="com.jr.handlers.MyHandler"/>
        </mvc:interceptor>
    </mvc:interceptors>
```