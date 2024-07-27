package com.atguigu.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;

public class main implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("main.onStartup");
    }
}
