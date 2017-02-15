package com.edutilos.test

import junit.framework.TestCase
import org.junit.AfterClass
import org.junit.Assert.*


class TestProgrammer:TestCase() {
   private var dao:ProgrammerDAO = ProgrammerDAOImpl()

    private fun insert() {
        dao.save(Programmer(1, "foo", 10 , 100.0 , true))
        dao.save(Programmer(2, "bar", 20, 200.0 , false))
    }


    @AfterClass
    public fun tear() {
        (dao as ProgrammerDAOImpl).closeFactory()
    }

    fun testSave() {
        insert()
        var list = dao.findAll()
        assertEquals(2, list!!.size)
        var p1 = list!![0]
        assertEquals(1,p1.id)
        assertEquals("foo", p1.name)
        assertEquals(10 , p1.age)
        assertEquals(100.0 , p1.wage, 0.0)
        assertEquals(true, p1.active)
        clean()
    }

    fun testUpdate() {
        insert()
        dao.update(6, Programmer(2, "newbar", 66, 666.6, false))
        var list = dao.findAll()
        var p2 = list!![1]
        assertEquals(6, p2.id)
        assertEquals("newbar", p2.name)
        assertEquals(66, p2.age)
        assertEquals(666.6, p2.wage, 0.0)
        assertEquals(false, p2.active)
        clean()
    }


    fun testDelete() {
        insert()
        var list = dao.findAll()
        assertEquals(2, list!!.size)
        dao.delete(3)
        list = dao.findAll()
        assertEquals(1, list!!.size)
        dao.delete(4)
        list = dao.findAll()
        assertEquals(0, list!!.size)
        clean()
    }

    private fun clean() {
        dao.deleteAll()
    }

}