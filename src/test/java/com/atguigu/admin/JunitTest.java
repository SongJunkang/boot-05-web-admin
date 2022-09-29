package com.atguigu.admin;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JunitTest
{

    Stream<String> cal1(int a, int b)
    {
        return Stream.of("apple","balala");
    }

    int cal(int a,int b){
        return a+b;

    }

    @DisplayName("简单断言测试")
    @Test
    void testSimpleAssertions(){
        int cal = cal(2,3);
        assertEquals(5,cal);

    }

    @Test
    @DisplayName("组合断言")
    void all(){
        assertAll("test",
                ()->assertTrue(true &&true,"结果不为true"),
                ()->assertEquals(1,1));

        System.out.println("====");
    }
}
