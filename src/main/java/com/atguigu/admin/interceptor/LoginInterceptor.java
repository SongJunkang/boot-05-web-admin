package com.atguigu.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查，
 * 1、配置好拦截器，要拦截哪些请求
 * 2、把这些配置放到容器中
 * 3、
 */


@Slf4j//打上日志，看下
public class LoginInterceptor implements HandlerInterceptor {


    //目标方法执行之前前置
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录检查逻辑。

        String requestURI = request.getRequestURI();
        log.info("拦截的请求路径是{}：",requestURI);
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser != null){
            return  true;
        }
        //未拦截住，跳转到登录页面。
        //第一次用重定向没有取到数据，无法在首先显示错误信息，直接空白，转用request,给请求域中放数据，然后转发request.
        //拿到转发器，然后直接转发到当前这个请求，这个请求下的foward。
//        session.setAttribute("msg","请先登录");
//        response.sendRedirect("/");

        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/").forward(request,response);


        return false;

    }


    //后置
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    //最终
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
