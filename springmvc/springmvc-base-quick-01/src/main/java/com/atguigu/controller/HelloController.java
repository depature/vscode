package com.atguigu.controller;

import com.atguigu.pojo.person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @RequestMapping(value = "springmvc/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(String name){
        System.out.println("HelloController.hello");
        return "hello springMvc"+name;
    }
    @RequestMapping("{account}/{password}")
    @ResponseBody
    public String login(@PathVariable String account,@PathVariable String password){
        System.out.println(password);
        return account;
    }
    @RequestMapping("json")
    @ResponseBody
    public String JsonController(@RequestBody person person){
    return person.toString();
    }
}
