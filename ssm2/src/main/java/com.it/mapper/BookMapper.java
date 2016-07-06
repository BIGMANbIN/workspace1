package com.it.mapper;


import com.it.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
public interface BookMapper {

    void saveBook(Book book);

    List<Book> findAllBook();

    Book findBookById(Integer id);

    void delBook(Integer id);

    void updateBook(Book book);

    Long count();

    Long countByParam(Map<String,Object> param);

    List<Book> findByPage(@Param("start") Integer start,@Param("size") Integer size);

    List<Book> findByParam(Map<String,Object> param);

    List<Book> findByDataTable(Map<String, Object> param);

    Long countByKeyWord(@Param("keyword") String keyword);
}
