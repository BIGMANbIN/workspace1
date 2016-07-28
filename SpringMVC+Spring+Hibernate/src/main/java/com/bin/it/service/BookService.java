package com.bin.it.service;


import com.bin.it.dao.BookDao;
import com.bin.it.dao.BookTypeDao;
import com.bin.it.dao.PublisherDao;
import com.bin.it.pojo.Book;
import com.bin.it.pojo.BookType;
import com.bin.it.pojo.Publisher;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class BookService {

    @Inject
    private BookDao bookDao;
    @Inject
    private BookTypeDao bookTypeDao;
    @Inject
    private PublisherDao publisherDao;

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public List<BookType> findAllBookType() {
        return bookTypeDao.findAll();
    }

    public List<Publisher> findAllPublisher() {
        return publisherDao.findAll();
    }

    public void saveBook(Book book) {
        bookDao.save(book);
    }

    public void delBook(Integer id) {
        bookDao.del(id);
    }

    public Book findById(Integer id) {
        return bookDao.findById(id);
    }
}
