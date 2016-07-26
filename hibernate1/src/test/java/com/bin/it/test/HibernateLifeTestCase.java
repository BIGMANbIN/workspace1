package com.bin.it.test;


import com.bin.it.pojo.User;
import com.bin.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class HibernateLifeTestCase {

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("李斯");
        user.setPassword("123456");
        user.setEmail("123@456han.com");

        session.persist(user);
        System.out.println(user.getId());

        session.getTransaction().commit();
    }

    @Test
    public void testFindByGet() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 9);

        session.getTransaction();

        Assert.assertNotNull(user);
    }

    @Test
    public void testFindByLoad() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 9);

        session.getTransaction().commit();

        user.setUsername("James");

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.update(user);
        session1.getTransaction().commit();
    }

    @Test
    public void testSaveOrUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("王五");
        user.setPassword("45679");

        session.saveOrUpdate(user);

        session.getTransaction().commit();

        user.setPassword("0123456789");
        Session session1 = HibernateUtil.getSession();

        session1.beginTransaction();

        session1.saveOrUpdate(user);

        session1.getTransaction().commit();
    }


    @Test
    public void testMerge() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 9);

        session.getTransaction().commit();

        user.setPassword("012345678");

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.merge(user);

        session1.getTransaction().commit();
    }

    @Test
    public void testDelete() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 9);

        session.getTransaction().commit();

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.delete(user);

        session1.getTransaction().commit();
    }

    @Test
    public void testClear() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 11);
        //session.clear();
        user.setPassword("321321");
        session.flush();//将对象的变化立即同步到数据库

        session.getTransaction().commit();
    }
}
