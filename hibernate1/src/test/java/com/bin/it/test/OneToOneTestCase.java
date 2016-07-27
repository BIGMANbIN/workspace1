package com.bin.it.test;

import com.bin.it.pojo.Card;
import com.bin.it.pojo.Person;
import com.bin.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/*
    一对一
*/
public class OneToOneTestCase {

    /*
        最佳实践
        先存主，再存从
     */
    @Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = new Person();
        person.setName("翟晋");

        Card card = new Card();
        card.setCardname("1004");
        card.setPerson(person);

        session.save(person);
        session.save(card);

        session.getTransaction().commit();
    }

    @Test
    public void testFindPerson() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class, 12);
        System.out.println(person.getName());
        System.out.println(person.getCard().getCardname());


        session.getTransaction().commit();
    }

    @Test
    public void testFindCard() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class, 11);
        System.out.println(card.getCardname());
        System.out.println(card.getPerson().getName());

        session.getTransaction().commit();
    }

    @Test
    public void testDeletePerson() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class, 11);
        session.delete(person);

        session.getTransaction().commit();
    }

    @Test
    public void testDeleteCard() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class, 10);
        session.delete(card);

        session.getTransaction().commit();
    }
}
