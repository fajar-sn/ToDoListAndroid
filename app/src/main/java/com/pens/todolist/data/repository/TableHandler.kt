package com.pens.todolist.data.repository

interface TableHandler<T> {
    fun create (t: T)
    fun readById(id: String): T
    fun update(t: T)
    fun delete(t: T)
}