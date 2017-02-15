package com.edutilos.test

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration


class ProgrammerDAOImpl:ProgrammerDAO {

    private var factory:SessionFactory? = null
    private var session:Session? = null
    private var tx:Transaction? = null

   constructor() {
       factory = Configuration()
       .configure()
       .addAnnotatedClass(Programmer::class.java)
       .buildSessionFactory()
   }
    private fun connect() {
          session = factory!!.openSession()
    }

    private fun disconnect() {
        session!!.close()
    }

    public fun closeFactory() {
         factory!!.close()
    }


    override fun save(p: Programmer) {
       connect()
        try {
            tx = session!!.beginTransaction()
            session!!.save(p)
            tx!!.commit()
        } catch(ex:Exception ){
            ex.printStackTrace()
            tx!!.rollback()
        } finally {
           disconnect()
        }
    }

    override fun update(id: Long, newP: Programmer) {
       connect()
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
            disconnect()
        }
    }

    override fun findAll(): MutableList<Programmer>? {
        var list:MutableList<Programmer>? = null
       connect()
        try {
            list = session!!.createQuery("from Programmer").list() as MutableList<Programmer>?
        } catch(ex:Exception) {
            ex.printStackTrace()
        } finally {
            disconnect()
        }

        return list
    }

    override fun findById(id: Long): Programmer? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        connect()
        try {
            tx = session!!.beginTransaction()
             var p = session!!.get(Programmer::class.java, id)
              session!!.delete(p)
            tx!!.commit()
        } catch(ex:Exception ){
            ex.printStackTrace()
            tx!!.rollback()
        } finally {
            disconnect()
        }

    }

    override fun deleteAll() {
       connect()
        var list = session!!.createQuery("from Programmer").list() as MutableList<Programmer>?
         try {
             tx = session!!.beginTransaction()
        list!!.forEach { p ->
            var pDelete = session!!.get(Programmer::class.java , p.id)
            session!!.delete(pDelete)
        }
             tx!!.commit()
         } catch(ex:Exception) {
             ex.printStackTrace()
             tx!!.rollback()
         } finally {

             disconnect()
         }
    }
}