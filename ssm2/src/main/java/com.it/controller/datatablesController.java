package com.it.controller;


import com.google.common.collect.Maps;
import com.it.pojo.Book;
import com.it.service.BookService;
import com.it.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/datatable")
public class DataTablesController {


    @Inject
    private BookService bookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){

        return "datatable/list";



    }

    @RequestMapping(value = "/data.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");//当前页偏移量
        String length = request.getParameter("length");//每页显示多少条数据
        String keyword = request.getParameter("search[value]");//搜索框中的值
        keyword = Strings.toUTF8(keyword);

        String sortColumnIndex = request.getParameter("order[0][column]");//获取排序列的索引
        String sortColumnName = request.getParameter("columns["+sortColumnIndex+"][name]");//根据排序列的索引获取列的名字
        String sortType = request.getParameter("order[0][dir]");//排序方式 asc | desc

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        param.put("keyword",keyword);
        param.put("sortColumnName",sortColumnName);
        param.put("sortType",sortType);

        List<Book> bookList = bookService.findByDtaTables(param);

        Map<String,Object> result = Maps.newHashMap();
        result.put("draw",draw);
        result.put("recordsTotal",bookService.count());
        result.put("recordsFiltered",bookService.countByKeyWord(keyword));
        result.put("data",bookList);
        return result;
    }
}
