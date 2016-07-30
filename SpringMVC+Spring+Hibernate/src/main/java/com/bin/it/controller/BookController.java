package com.bin.it.controller;


import com.bin.it.pojo.Book;
import com.bin.it.pojo.BookType;
import com.bin.it.pojo.Publisher;
import com.bin.it.service.BookService;
import com.bin.it.util.Page;
import com.bin.it.util.SearchParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Inject
    private BookService bookService;

    //显示列表
    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam(name = "p",defaultValue = "1",required = false)Integer pageNo,Model model,HttpServletRequest request) {

        //List<Book> bookList = bookService.findAll();
        List<SearchParam> searchParamList = SearchParam.buiderSearchParam(request);
        Page<Book> page = bookService.findByPage(pageNo,searchParamList);
        model.addAttribute("page", page);
        return "book/list";
    }

    //新增书籍

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBook(Model model) {
        List<BookType> bookTypeList = bookService.findAllBookType();
        List<Publisher> publisherList = bookService.findAllPublisher();

        model.addAttribute("bookTypeList", bookTypeList);
        model.addAttribute("publisherList", publisherList);
        return "book/new";
    }

    //保存书籍

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveBook(Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);

        redirectAttributes.addFlashAttribute("message", "添加成功");
        return "redirect:/book";
    }

    //删除书籍

    @RequestMapping(value = "/{id:\\d+}/del", method = RequestMethod.GET)
    public String delBook(@PathVariable Integer id) {
        bookService.delBook(id);
        return "redirect:/book";
    }

    //修改书籍

    @RequestMapping(value = "/{id:\\d+}/edit", method = RequestMethod.GET)
    public String editBook(Model model, @PathVariable Integer id) {
        Book book = bookService.findById(id);
        List<BookType> bookTypeList = bookService.findAllBookType();
        List<Publisher> publisherList = bookService.findAllPublisher();

        model.addAttribute("bookTypeList",bookTypeList);
        model.addAttribute("publisherList",publisherList);
        model.addAttribute("book",book);

        return "book/edit";
    }

    @RequestMapping(value = "/{id:\\d+}/edit", method = RequestMethod.POST)
    public String editBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/book";
    }

}


