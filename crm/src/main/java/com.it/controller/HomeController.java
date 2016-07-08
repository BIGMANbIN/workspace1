package com.it.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {

    /**
     * 去登录页面
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    /**
     * 验证账号或密码是否正确并给出提示
     *
     * @param username
     * @param password
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(String username, String password, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            //当前用户已经登录，则退出当前账号
            subject.logout();
        }

        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            return "redirect/home";
        } catch (AuthenticationException exception) {

            redirectAttributes.addAttribute("message", "账号或密码错误");
            return "redirect/";
        }
    }

    @RequestMapping("/home")
    public String Home() {
        return "home";
    }

    @RequestMapping("/table")
    public String table() {
        return "table";
    }
}
