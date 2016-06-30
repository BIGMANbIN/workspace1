package test;


import com.it.dao.UserDaoImpl1;
import com.it.dao.UserDaoImpl2;
import com.it.service.BookService;
import com.it.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemoTestCase {

    //工厂模式
    //默认情况下，Spring容器中管理的类将会变成单例类，类的对象会在容器启动时创建
    //懒汉 饿汉


    @Test
    public void testSayHi() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

       /* UserService userService = (UserService) context.getBean("userService");
        userService.sayHi();

        UserDaoImpl1 userDao1 = (UserDaoImpl1) context.getBean("userDao1");

        userDao1.save();


        UserDaoImpl2 userDao2 = (UserDaoImpl2) context.getBean("userDao2");

        userDao2.save();*/

       /* BookService bookService = (BookService) context.getBean("bookService");
        bookService.showBooks();
        */

        UserService userService = (UserService) context.getBean("userService");
        userService.sayHello();
    }
}
