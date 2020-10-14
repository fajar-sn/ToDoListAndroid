package com.example.todolist3.module.todolist

class ToDoListPresenter(private val view: ToDoListContract.View) : ToDoListContract.Presenter {

    override fun addTask() {
        view.redirectToFormLayout()
    }

    override fun start() {}

}