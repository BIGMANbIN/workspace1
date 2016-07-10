package com.it.controller;

import com.it.pojo.User;
import com.it.service.UserService;
import com.it.util.ShiroUtil;
import org.apache.commons.codec.digest.DigestUtils;
import com.it.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Inject
    private UserService userService;

    /**
     * 修改密码
     */

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String editPassword() {

        return "setting/password";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ResponseBody
    public String editPassword(String password) {

        userService.changePassword(password);
        return "success";
    }

    /**
     * 验证原始密码是否正确(ajax调用)
     *
     * @return
     */
    @RequestMapping(value = "/validate/password",method = RequestMethod.GET)
    @ResponseBody
    public String validateOldPassword(@RequestHeader("x-Requested-with") String xRequestedWith, String oldpassword){

        if ("XMLHttpRequest".equals(xRequestedWith)){

            User user = ShiroUtil.getCurrentUser();
            if (user.getPassword().equals(DigestUtils.md5Hex(oldpassword))){
                return "true";
            }
            return "false";
        }else{
            throw new NotFoundException();
        }
    }
}
