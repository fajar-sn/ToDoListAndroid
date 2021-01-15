package com.pens.todolist.module.todolist

import com.pens.todolist.base.BasePresenter
import com.pens.todolist.base.BaseView
import com.pens.todolist.data.model.Task

interface ToDoListContract {
    interface Presenter : BasePresenter {
        fun getDataSet(): List<Task>?
        fun update(task: Task)
        fun getData(id: Int): Task?
    }

    interface View : BaseView<Presenter> {
        fun redirectToFormLayout()
    }
}