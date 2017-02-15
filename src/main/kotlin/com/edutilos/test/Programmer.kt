package com.edutilos.test

import javax.persistence.*


@Entity
@Table(name = "Programmer")
class Programmer(val MyId:Long , val MyName: String , val MyAge:Int , val MyWage:Double, val MyActive:Boolean) {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = MyId
    var name = MyName
    var age = MyAge
    var wage = MyWage
    var active = MyActive

    constructor(): this(0, "", 0, 0.0 , false) {

    }

    override fun toString(): String {
        return "Programmer(id=$id, name='$name', age=$age, wage=$wage, active=$active)"
    }


}