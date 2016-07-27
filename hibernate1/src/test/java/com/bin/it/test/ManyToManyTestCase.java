package com.bin.it.test;


import com.bin.it.pojo.Student;
import com.bin.it.pojo.Teacher;
import com.bin.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyTestCase {

    /*
        最佳实践
        保存时让一方放弃维护
     */
    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher1 = new Teacher();
        teacher1.setTeachername("T3");

        Teacher teacher2 = new Teacher();
        teacher2.setTeachername("T4");

        Student student1 = new Student();
        student1.setStudentname("S3");

        Student student2 = new Student();
        student2.setStudentname("S4");

        Set<Teacher> teacherSet = new HashSet<Teacher>();
        teacherSet.add(teacher1);
        teacherSet.add(teacher2);

        student1.setTeacherSet(teacherSet);
        student2.setTeacherSet(teacherSet);

        session.save(teacher1);
        session.save(teacher2);

        session.save(student1);
        session.save(student2);

        session.getTransaction().commit();
    }

    @Test
    public void testFind() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class, 6);
        System.out.println(teacher.getTeachername());

        Set<Student> studentSet = teacher.getStudentSet();
        for (Student student : studentSet) {
            System.out.println(student.getId() + "->" + student.getStudentname());
        }


        session.getTransaction().commit();
    }
}
