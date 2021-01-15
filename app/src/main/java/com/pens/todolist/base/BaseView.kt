package com.pens.todolist.base

interface BaseView<T> {
    fun setPresenter(presenter : T)
}