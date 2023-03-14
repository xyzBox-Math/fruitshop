package com.example.fruitshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GetUserController {
    @RequestMapping("/getPort.do")
    public String getPort(HttpSession httpSession, HttpServletRequest httpServletRequest){
        String usr="";
        usr+=httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()
        +httpServletRequest.getContextPath();
        httpSession.setAttribute("user",usr);
        return "portTest";
    }
}