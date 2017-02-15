package com.edutilos.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PersonTest {
    public static void main(String[] args) {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();
       PersonTest test = new PersonTest();
        test.save(new Person("foo", 10 , 100.0 , true));
        test.save(new Person("bar", 20 , 200.0 , false));
        List<Person> all = test.findAll();
        all.forEach(p -> System.out.println(p));
        factory.close();
    }


    private static SessionFactory factory ;
    private Session session;
    private Transaction tx ;


    public void save(Person p) {
        session = factory.openSession();
        try {
            tx = session.beginTransaction();
            session.save(p);
            tx.commit();
        } catch(Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public List<Person> findAll() {
        List<Person> list = null ;
        session = factory.openSession();
        try {
            list = session.createQuery("from Person").list();
        } catch(Exception ex){
            ex.printStackTrace();

        } finally {
            session.close();
        }
        return list ;
    }
}
