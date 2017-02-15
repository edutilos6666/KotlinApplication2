package com.edutilos.test

import groovy.transform.Canonical

import javax.persistence.*


@Entity
@Table(name  = "Worker")
@Canonical
class Worker {
    @Id
    @GeneratedValue
  long id
    String name
    int age
    double wage
    boolean active
}
