package com.it.controller;


import com.google.common.collect.Maps;
import com.it.dto.DataTablesResult;
import com.it.pojo.Notice;
import com.it.service.NoticeService;
import com.it.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RequestMapping("/notice")
@Controller
public class NoticeController {

    @Inject
    private NoticeService noticeService;


    @RequestMapping(method = RequestMethod.GET)
    public String noticelist(){
        return "notice/noticelist";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult loadNotice(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");

        Map<String,Object> param = Maps.newHashMap();

        param.put("start",start);
        param.put("length",length);

        List<Notice> noticeList = noticeService.findByParam(param);
        Long count = noticeService.count();
        return new DataTablesResult(draw,noticeList,count,count);
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

    /**
     * 根据ID显示公告内容
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String viewNotice(@PathVariable Integer id, Model model){

        Notice notice = noticeService.findById(id);
        if(notice == null){
            throw new NotFoundException();
        }
        model.addAttribute("notice",notice);
        return "notice/view";
    }






}
