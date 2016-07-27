package com.bin.it.test;


import com.bin.it.pojo.User;
import com.bin.it.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

public class HibernateTestCase {

    @Test
    public void testSave() {

        Configuration configuration = new Configuration().configure();

        //hibernate 3.x
        //SessionFactory sessionFactory = configuration.buildSessionFactory();

        //hibernate 4.3
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("linlin");
        user.setPassword("123456");
        user.setEmail("linlin@163.com");

        session.save(user);

        session.getTransaction().commit();
        //session.close();

    }

    @Test
    public void testFindById() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 6);
        System.out.println(user.getUsername());
        session.getTransaction().commit();
    }

    @Test
    public void testUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 6);
        user.setPassword("345678");
        user.setUsername("张三丰");

        session.getTransaction().commit();
    }

    @Test
    public void testDel() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 7);
        session.delete(user);

        session.getTransaction().commit();
    }

    @Test
    public void testFidAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        for (User user : userList) {
            System.out.println(user.getId() + "->" + user.getUsername());
        }

        session.getTransaction().commit();
    }
}
