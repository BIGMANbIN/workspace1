package com.it.service;


import com.it.mapper.BookMapper;
import com.it.mapper.BookTypeMapper;
import com.it.mapper.PublisherMapper;
import com.it.pojo.Book;
import com.it.pojo.BookType;
import com.it.pojo.Publisher;
import com.it.util.Page;
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


    public List<BookType> findAllBookType(){
        return bookTypeMapper.findAll();
    }

    public List<Publisher> findAllPublisher(){
        return publisherMapper.findAll();
    }

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

    public Page<Book> findBookPge(Integer p) {

        int totalSize = bookMapper.count().intValue();

        Page<Book> page = new Page<>(p,5,totalSize);

        List<Book> bookList = bookMapper.findByPage(page.getStart(),5);
        page.setItems(bookList);
        return page;
    }
}
