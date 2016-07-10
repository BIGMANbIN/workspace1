package com.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 修改密码
     */

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String editPassword() {

        return "setting/password";
    }
}
