package com.example.todolist3.module.form

class FormPresenter(private val view: FormContract.View) : FormContract.Presenter {

    override fun addToDoTask(task : String) {
        view.redirectToList()
    }

    override fun start() {}

}