package com.it.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping("/home")
    public String Home(){
        return "home";
    }

    @RequestMapping("/table")
    public String table(){
        return "table";
    }
}