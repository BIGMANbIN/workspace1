package com.bin.it.test;


import com.bin.it.pojo.User;
import com.bin.it.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HQLTestCase {

    @Test
    public void testFindAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        for (User user : userList) {
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testFindByWhere() {
        //HQL全部都是Java中的对象，跟数据无无关
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User as u where u.password =:password and u.username=:name";//：引用占位符

        Query query = session.createQuery(hql);
        query.setParameter("password", "123456789");
        query.setParameter("name", "rose");

        List<User> userList = query.list();

        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();

    }

    @Test
    public void testFindUnique() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User where password = :pwd";
        Query query = session.createQuery(hql);
        query.setParameter("pwd", "123123123");

        User user = (User) query.uniqueResult();//确保结果集只有一条记录
        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    public void testFindByCloumn() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select id,username,password from User";
        Query query = session.createQuery(hql);

        List<Object[]> result = query.list();

        for (Object[] object : result) {
            System.out.println(object[0] + "->" + object[1] + "->" + object[2]);
        }

        /*List<String> result = query.list();

        for (String name : result){
            System.out.println(name);
        }*/
        session.getTransaction().commit();
    }

    @Test
    public void testCount() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select count(*),max(id) from User";
        Query query = session.createQuery(hql);
        Object[] objects = (Object[]) query.uniqueResult();
        System.out.println("count" + objects[0]);
        System.out.println("max" + objects[1]);

        session.getTransaction();
    }

    @Test
    public void testPage() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User order by id asc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(3);

        List<User> userList = query.list();

        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction();
    }
}
