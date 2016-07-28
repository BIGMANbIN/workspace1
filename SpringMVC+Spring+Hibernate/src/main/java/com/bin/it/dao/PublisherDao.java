package com.bin.it.dao;


import com.bin.it.pojo.BookType;
import com.bin.it.pojo.Publisher;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PublisherDao {

    @Inject
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Publisher> findAll(){
        Criteria criteria = getSession().createCriteria(Publisher.class);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }
}
