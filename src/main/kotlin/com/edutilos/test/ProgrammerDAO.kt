package com.edutilos.test


interface ProgrammerDAO {
  public fun save(p:Programmer)
    public fun update(id:Long , p:Programmer)
    public fun findAll(): MutableList<Programmer>?
    public fun findById(id:Long):Programmer?
    public fun delete(id:Long)
    public fun deleteAll()

}