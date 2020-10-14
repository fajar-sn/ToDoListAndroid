package com.example.todolist3.module.todolist

import com.example.todolist3.base.BasePresenter
import com.example.todolist3.base.BaseView


interface ToDoListContract {
    interface Presenter : BasePresenter {
        fun addTask()
    }

    interface View : BaseView<Presenter> {
        fun redirectToFormLayout()
    }
}