package com.it.mapper;

import com.it.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    void saveBook(Book book);
    List<Book> findAllBook();
    Book findBookById(Integer id);
    void delBook(Integer id);
    void updateBook(Book book);

    Long count();

    List<Book> findByPage(@Param("start") Integer start,@Param("size") Integer size);
}
