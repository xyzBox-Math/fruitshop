package com.example.fruitshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    @RequestMapping("/test.do")
    public String test(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("url","index.html");
        return "common";
    }
}
