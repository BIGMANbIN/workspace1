package com.it.controller;


import com.it.pojo.Notice;
import com.it.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@RequestMapping("/notice")
@Controller
public class NoticeController {

    @Inject
    private NoticeService noticeService;

    @RequestMapping(method = RequestMethod.GET)
    public String noticelist(){
        return "notice/noticelist";
    }

    /**
     * 发表公告
     * @return
     */
    @RequestMapping(value = "/noticenew",method = RequestMethod.GET )
    public String newNotice(){
        return "notice/noticenew";
    }

    @RequestMapping(value = "/noticenew",method = RequestMethod.POST)
    public String newNotice(Notice notice, RedirectAttributes redirectAttributes){
        noticeService.saveNotice(notice);
        redirectAttributes.addFlashAttribute("message","发表成功");
        return "redirect:/notice";

    }

}
