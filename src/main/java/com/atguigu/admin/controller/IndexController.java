package com.atguigu.admin.controller;


import com.atguigu.admin.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody//使它响应出去信息，就不跳转页面了。
    @GetMapping("sql")
    public String queryFromBd(){

        Long aLong = jdbcTemplate.queryForObject("select count(*) from t_book", Long.class);
        return aLong.toString();

    }



    @GetMapping({"/","/login"})
    public String loginPage(){

        return "login";
    }



    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){

        if (StringUtils.hasText(user.getUserName())  &&  "123456".equals(user.getPassword())){

            //只要不为空，就先默认为输入了账号密码：
            //把登录成功的用户存起来
            session.setAttribute("loginUser",user);
            //登录成功，重定向到main页面。
            return "redirect:main.html";

        }else {

            model.addAttribute("msg","账号密码错误");
            return "login";
        }

    }

//这样不会导致之前的表单重复提交，已经到了下一个页面了。重定向防止表单重新提交。

    //真正的去main页面。

    @GetMapping("main.html")
    public String mainPage(HttpSession session,Model model){

        //判断是否登录，拦截器，过滤器。
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser != null)
        {
            return "main";
        }else {

            model.addAttribute("msg","请重新登录。");
            return "login";
        }


    }


}
