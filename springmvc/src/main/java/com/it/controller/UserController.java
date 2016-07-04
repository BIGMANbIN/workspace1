package com.it.controller;

import com.it.pojo.User;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;


@Controller
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //页面传值 1.Model
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model,
                       @RequestParam(required = false, defaultValue = "false") String vip,
                       Integer age,
                       @RequestHeader(value = "User-Agent", required = false, defaultValue = "other") String UserAgent,

                       @CookieValue(value = "JSESSIONID", required = false, defaultValue = "") String cookie) {
     /*   User user = new User();
        user.setUsername("HANHan");*/
        logger.debug("User-Agent:{}", UserAgent);
        logger.debug("vip:{} age:{}", vip, age);
        logger.debug("cookieId:{}", cookie);
        model.addAttribute("userName", "Tom");
        return "users/list";
    }

    //获取cookies


    //2.返回值变成ModelAndView
    @RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.GET)
    public ModelAndView showUsers(@PathVariable Integer userId) {
        logger.debug("ID:{}", userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users/show");
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

    /* @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
     public String showUser(@PathVariable Integer id) {
         logger.debug("ID:{}", id);
         return "users/show";
     }*/
    @RequestMapping(value = "/{userId:\\d+}/photos/catalog/{catalogId:\\d+}", method = RequestMethod.GET)
    public String PhotoUser(@PathVariable Integer userId, @PathVariable Integer catalogId) {
        logger.debug("UserId:{} CatalogId:{}", userId, catalogId);
        return "users/photos";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser() {
        return "users/newuser";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveUser(User user, String comment) {
        logger.debug("username:{} address:{} age:{} comment:{}", user.getUsername(), user.getAddress(), user.getAge(), user.getComment());
        return "redirect:/users";
    }

    @RequestMapping(value = "/{id:\\d+}/del", method = RequestMethod.GET)
    public String del(@PathVariable Integer id) {
        logger.debug("del object {}", id);
        return "redirect:/users";
    }


    //----------------ajax


    @RequestMapping(value = "/checkUser.json", method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkUser(@PathVariable Integer id) {

        logger.debug("ID:{}", id);
        return "success";

    }


    @RequestMapping(value = "/{id}.json", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    @ResponseBody
    public User showUserJson() {
        User user = new User();
        user.setUsername("张三");
        user.setAddress("北京");
        user.setAge(50);
        return user;
    }


    //-----------Native Servlet

    @RequestMapping(value = "/native")
    public String nativeHttpServlet(HttpServletRequest request,
                                    HttpServletResponse response,
                                    HttpSession session) {

        ServletContext context = session.getServletContext();

        session.setAttribute("curr_user", "Jim");
        return "home";
    }


    //-----------------fileUpload

    @RequestMapping(value = "/avatar/upload", method = RequestMethod.GET)
    public String uploadAavatr() {

        return "users/upload";
    }


    @RequestMapping(value = "/avatar/upload", method = RequestMethod.POST)
    public String uploadAavatr(String photoname, MultipartFile file) {

        //判断用户是都选择了文件，需要使用file.isEmpty()来判断，为不能用是否为NULL来判断

        logger.debug("------------------------");
        logger.debug("PhotoName:{}", photoname);
        logger.debug("File Content Type:{}", file.getContentType());
        logger.debug("File Size:{}", file.getSize());
        logger.debug("File isEmpty:{}", file.isEmpty());
        logger.debug("File Name:{}", file.getOriginalFilename());
        logger.debug("------------------------");


        try {
            IOUtils.copy(file.getInputStream(), new FileOutputStream("E:/" + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/users";
    }
}
