package com.atguigu.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest//Junit5,具有spring的功能。
class Boot05WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        Long aLong = jdbcTemplate.queryForObject("select count(*) from t_book", Long.class);
        log.info("记录总数：{}",aLong);

//        jdbcTemplate.queryForList("select * from t_book");

//        ArrayList list = ["a","b"];
        log.info("数据源类型：{}.",dataSource.getClass());
        log.info("数据源类型:  ",dataSource.getClass());//{}是log。info的占位符， placeholders ，不能省略

    }
    @Test
    void testList(){

        List list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.remove("c");
        list.remove(3);


        System.out.println(list);
    }


    @Test
    void  testRedis(){
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set("hello",  "world");

        String hello = stringStringValueOperations.get("hello");
        System.out.println(hello);
    }

}
