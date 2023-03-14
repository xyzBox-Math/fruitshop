package com.example.fruitshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FlyController {
    @RequestMapping("/fly.do")
    public String fly(){
        return "fly";
    }
}
