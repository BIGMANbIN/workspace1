package test.Service;


import com.it.pojo.Book;
import com.it.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookServiceTestCase {

    Logger logger = LoggerFactory.getLogger(BookServiceTestCase.class);
    @Inject
    private BookService bookService;

    @Test
    public void testSaveBook(){

        Book book = new Book();
        book.setBookname("把时间当朋友");
        book.setBookprice(80F);
        book.setBookauthor("里维斯");
        book.setBooknum(10);
        book.setTypeid("1");
        book.setPubid("2");

        bookService.saveBook(book);

    }

    @Test
    public void testFindAllBook(){

        List<Book> bookList = bookService.findAllBook();
        Assert.assertEquals(bookList.size(),30);
    }

    @Test
    public void testFindBookById(){

        Book book = bookService.findBookById(36);
        Assert.assertNotNull(book);
    }

    @Test
    public void testDelBook(){

        bookService.delBook(34);

    }

    @Test
    public void testUpdateBook(){

        Book book = bookService.findBookById(34);
        book.setBookname("把时间当朋友");
        bookService.updateBook(book);
    }
}
