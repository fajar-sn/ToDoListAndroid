package com.example.todolist3.module.form

import com.example.todolist3.base.BasePresenter
import com.example.todolist3.base.BaseView


interface FormContract {
    interface Presenter : BasePresenter {
        fun addToDoTask(task : String)
    }

    interface View : BaseView<Presenter> {
        fun redirectToList(task: String)
    }
}