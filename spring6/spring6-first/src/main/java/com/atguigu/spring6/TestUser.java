package com.atguigu.spring6;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestUser {
    public static void main(String[] args) {
        ApplicationContext context=new
                ClassPathXmlApplicationContext("bean.xml");
        User user1= (User)context.getBean("user");
        System.out.println(user1.getName());




//        User user3=context.getBean("user",User.class);
//        System.out.println("3"+user3);
    }
}
