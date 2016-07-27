package com.bin.it.test;


import com.bin.it.pojo.Task;
import com.bin.it.util.HibernateUtil;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Cache;
import org.junit.Test;



public class UuidPrimaryKeyTestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = new Task();
        task.setTitle("task-4");

        session.save(task);

        session.getTransaction().commit();
    }

    @Test
    public void testFindById(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        //一级缓存
        Task task = (Task) session.get(Task.class,"297e08ff562b233b01562b233d970000");
        //System.out.println(session.contains(task));
        //session.clear();//清楚一级缓存（将session中的缓存清除）
        //session.evict(task);//清除指定对象的缓存

        session.getTransaction().commit();

        //将对象从二级缓存中清除
        Cache cache = HibernateUtil.getSessionFactory().getCache();
        cache.evictEntityRegion(Task.class);
        cache.evictAllRegions();

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        Task task2 = (Task) session2.get(Task.class,"297e08ff562b22d801562b22da720000");
        System.out.println(task2.getTitle());

        session2.getTransaction().commit();
    }

    @Test
    public void testVersion() throws InterruptedException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"297e08ff562bd8e701562bd8e90d0000");
        task.setTitle("xx-101");

        Thread.sleep(3000);


        session.getTransaction().commit();
    }

    @Test
    public void  testUpdate() throws InterruptedException {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();

        final Task task = (Task) session.get(Task.class,"297e08ff562bd8e701562bd8e90d0000");
        task.setTitle("task-5");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();

                Task task1 = (Task) session1.get(Task.class,"297e08ff562bd8e701562bd8e90d0000", LockOptions.UPGRADE);
                task1.setTitle("Task-7");

                session1.getTransaction().commit();
            }
        });
        thread.start();

        Thread.sleep(3000);

        session.getTransaction().commit();

    }

}
