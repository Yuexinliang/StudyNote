package com.jr.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * className MyHandler
 * packageName com.jr.util
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/08 15:25
 */
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
