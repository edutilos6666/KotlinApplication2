package com.edutilos.test

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration


class WorkerTest {
    public static void main(String[] args) {
      factory = new Configuration()
        .configure()
        .addAnnotatedClass(Worker.class)
        .buildSessionFactory()

        def test = new WorkerTest()
        test.save(new Worker(1, "foo",10 ,100.0 , false ))
        test.save(new Worker(2, "bar", 20, 200.0 , true))

        def list = test.findAll()
        list.each {el ->
 println(el)
        }

        factory.close()
    }

  private static SessionFactory factory
    private Session session
    private Transaction tx


    public void save(Worker w) {
       session = factory.openSession()
        try {
            tx = session.beginTransaction()
             session.save(w)
            tx.commit()
        } catch(Exception ex) {
          ex.printStackTrace()
            tx.rollback()
        } finally {
            session.close()
        }
    }

    public List<Worker> findAll() {
      List<Worker> list
        session = factory.openSession()

        try {
           list = session.createQuery("from Worker").list()
        } catch(Exception ex) {
            ex.printStackTrace()
        } finally {
            session.close()
        }
        return list
    }

}
