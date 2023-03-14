package com.example.fruitshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/fIndex.do")
    public String fIndex(){
        return "index";
    }
}
