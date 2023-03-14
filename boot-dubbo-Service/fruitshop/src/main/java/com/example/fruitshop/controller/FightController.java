package com.example.fruitshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FightController {
    @RequestMapping("/fight.do")
    public String fight(){
        return "fight";
    }
}
