package com.atguigu.admin.controller;

import com.atguigu.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {


    @GetMapping("/basic_table")
    public String basic_table(@RequestParam("a") int a){


        int i = 10/0;
        return "table/basic_table";

    }


    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model){

    //表格内容的遍历:
        List<User> users = Arrays.asList(new User("zhangsan", "123456"),
                new User("lisi", "123444"),
                new User("haha", "aaaaa"),
                new User("hehe", "aaddd"));

        //放入请求域中
        model.addAttribute("users",users);
        return "table/dynamic_table";

    }


    @GetMapping("/editable_table")
    public String editable_table(){

        return "table/editable_table";
    }


    @GetMapping("/responsive_table")
    public String responsive_table(){


        return "table/responsive_table";
    }
}
