package com.atguigu.admin.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/bm")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("啊34312");
    }

//    @Configuration(proxyBeanMethods = true)//保证依赖的组件始终是单实例模式。
//    public ServletRegistrationBean myServlet(){
//        MyServlet myServlet = new MyServlet();
//        return new ServletRegistrationBean(myServlet);
//    }
}
