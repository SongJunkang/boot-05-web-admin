package com.atguigu.admin.bean;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor//全有参构造
@NoArgsConstructor//无参构造器。
@Data
@TableName("t_user")
public class User {

    private String userName;
    private String password;

}
