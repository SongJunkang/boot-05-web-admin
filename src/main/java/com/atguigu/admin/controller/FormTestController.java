package com.atguigu.admin.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Slf4j
@Controller
public class FormTestController {


    @GetMapping("/form_layouts")
    public String form_layouts(){

        return "form/form_layouts";
    }

    /**
     * MultipartFile 自动封存上传过来的文件。多元文件名字。
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,//表单的获取。
                         @RequestPart("photos")  MultipartFile[] photos) throws IOException {



        log.info("打印上传的信息：email ={},username= {},headerImg ={},headerImg={}",
                        email,username,headerImg.getSize(),photos.length);

        if(!headerImg.isEmpty()){
            //保存到文件服务器，OSS服务器
            String originalFilename = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("C:\\cache\\"+originalFilename));
        }

        if(photos.length > 0){
            for (MultipartFile photo : photos) {
                if(!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("C:\\cache\\"+originalFilename));
                }
            }
        }

        return "main";//逻辑视图
    }

}
