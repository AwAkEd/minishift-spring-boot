package com.entelgydigital.minishift;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("hello")
    public String sayHello(){
        return ("Hello, SpringBoot running on Wildfly over Minishift!!");
    }
}