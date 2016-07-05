package com.it.controller;


import com.it.exception.NotFoundException;
import com.it.mapper.BookTypeMapper;
import com.it.pojo.Book;
import com.it.pojo.BookType;
import com.it.pojo.Publisher;
import com.it.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Inject
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Book> bookList = bookService.findAllBook();

        model.addAttribute("bookList",bookList);
        return "/books/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String saveBook(Model model){

        List<Publisher> publishers = bookService.findAllPublisher();
        List<BookType> bookTypes =bookService.findAllBookType();

        model.addAttribute("pus",publishers);
        model.addAttribute("types",bookTypes);
        return "/books/new";
    }


    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String saveBook(Book book, RedirectAttributes redirectAttributes){
        bookService.saveBook(book);

        redirectAttributes.addFlashAttribute("message","保存成功");
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String delBook(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        bookService.delBook(id);

        redirectAttributes.addAttribute("message","操作成功");
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String editBook(@PathVariable Integer id,Model model){

        Book book = bookService.findBookById(id);

        if (book == null){
            throw new NotFoundException();
        }

        model.addAttribute("types",bookService.findAllBookType());
        model.addAttribute("pubs",bookService.findAllPublisher());
        model.addAttribute("book",book);
        return "books/edit";
    }


    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.POST)
    public String editBookd(Book book,RedirectAttributes redirectAttributes){
        bookService.updateBook(book);

        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/books";
    }
}
