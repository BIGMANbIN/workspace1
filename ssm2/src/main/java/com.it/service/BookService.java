package com.it.service;


import com.it.mapper.BookMapper;
import com.it.mapper.BookTypeMapper;
import com.it.mapper.PublisherMapper;
import com.it.pojo.Book;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

@Named
@Transactional
public class BookService {

    @Inject
    private BookMapper bookMapper;
    @Inject
    private BookTypeMapper bookTypeMapper;
    @Inject
    private PublisherMapper publisherMapper;


    public void saveBook(Book book) {

        bookMapper.saveBook(book);
    }

    public List<Book> findAllBook() {
        return bookMapper.findAllBook();

    }

    public Book findBookById(Integer id){
        return bookMapper.findBookById(id);
    }

    public void delBook(Integer id){
        bookMapper.delBook(id);
    }

    public void updateBook(Book book){
        bookMapper.updateBook(book);
    }

}
