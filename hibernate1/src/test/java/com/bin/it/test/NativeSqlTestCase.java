package com.bin.it.test;

import com.bin.it.pojo.User;
import com.bin.it.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

//使用原生SQL查询
public class NativeSqlTestCase {

    @Test
    public void testFindAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user";
        Query query = session.createSQLQuery(sql);

        List<Object[]> result = query.list();

        for (Object[] objects : result) {
            System.out.println(objects[0] + ":" + objects[1] + ":" + objects[2]);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testFindById() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user where id= :id ";
        Query query = session.createSQLQuery(sql);
        query.setInteger("id", 11);

        Object[] objects = (Object[]) query.uniqueResult();
        System.out.println(objects[0] + ":" + objects[1]);


        session.getTransaction().commit();
    }

    @Test
    public void testFindByIdToUser() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user where id = :id";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.setInteger("id", 11);

        User user = (User) query.uniqueResult();
        System.out.println(user);

        session.getTransaction().commit();
    }
}
