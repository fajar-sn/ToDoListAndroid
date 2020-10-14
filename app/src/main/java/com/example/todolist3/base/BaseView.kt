package com.example.todolist3.base

interface BaseView<T> {
    fun setPresenter(presenter : T)
}