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
import java.util.Map;

@Named
@Transactional
public class BookService {

    @Inject
    private BookMapper bookMapper;
    @Inject
    private BookTypeMapper bookTypeMapper;
    @Inject
    private PublisherMapper publisherMapper;


    public List<BookType> findAllBookType() {
        return bookTypeMapper.findAll();
    }

    public List<Publisher> findAllPublisher() {
        return publisherMapper.findAll();
    }

    public void saveBook(Book book) {

        bookMapper.saveBook(book);
    }

    public List<Book> findAllBook() {
        return bookMapper.findAllBook();

    }


    public Book findBookById(Integer id) {
        return bookMapper.findBookById(id);
    }

    public void delBook(Integer id) {
        bookMapper.delBook(id);
    }

    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    public Page<Book> findBookPge(Integer p, Map<String, Object> param) {

        int totalSize = bookMapper.countByParam(param).intValue();

        Page<Book> page = new Page<>(p, 5, totalSize);

        param.put("start", page.getStart());
        param.put("size", 5);
        List<Book> bookList = bookMapper.findByParam(param);
        page.setItems(bookList);
        return page;
    }

    public List<Book> findByDtaTables(Map<String, Object> param) {
        return bookMapper.findByDataTable(param);
    }

    public Long count() {
        return bookMapper.count();
    }

    /*public Long countByKeyWord(String keyword) {
        return bookMapper.countByKeyWord(keyword);
    }*/


    public Long countByParam(Map<String, Object> param) {
        return bookMapper.countByParam(param);
    }
}
