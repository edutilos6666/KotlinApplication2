package com.edutilos.test

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration


object ProgrammerTest {
    @JvmStatic
    fun main(args: Array<String>) {
       factory =  Configuration()
        .configure()
        .addAnnotatedClass(Programmer::class.java)
        .buildSessionFactory()

        save(Programmer(1, "foo", 10, 100.0, true))
        save(Programmer(2, "bar", 20 , 200.0 , false))
        var list = findAll()
        list!!.forEach { el -> println(el) }
        factory!!.close()
    }


    private var factory:SessionFactory? = null
    private var session:Session? = null
    private var tx:Transaction? = null

    fun save(p:Programmer) {
      session = factory!!.openSession()
       try {
           tx = session!!.beginTransaction()
            session!!.save(p)
           tx!!.commit()
       } catch(ex:Exception ){
           ex.printStackTrace()
           tx!!.rollback()
       } finally {
           session!!.close()
       }
    }

    fun findAll(): MutableList<Programmer>? {
          var list:MutableList<Programmer>? = null
        session = factory!!.openSession()
        try {
            list = session!!.createQuery("from Programmer").list() as MutableList<Programmer>?
        } catch(ex:Exception) {
            ex.printStackTrace()
        } finally {
            session!!.close()
        }

        return list
    }


    fun update(id:Long , newP:Programmer) {
        session = factory!!.openSession()
        try {
            tx = session!!.beginTransaction()
            var p = session!!.get(Programmer::class.java , id)
            p.name = newP.name
            p.age = newP.age
            p.wage = newP.wage
            p.active = newP.active
            session!!.update(p)
            tx!!.commit()
        } catch(ex:Exception) {
          ex.printStackTrace()
            tx!!.rollback()
        } finally {
          session!!.close()
        }
    }
}