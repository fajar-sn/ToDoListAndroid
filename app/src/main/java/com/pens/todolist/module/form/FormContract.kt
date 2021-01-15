package com.pens.todolist.module.form

import com.pens.todolist.base.BasePresenter
import com.pens.todolist.base.BaseView
import com.pens.todolist.data.model.Task
import com.pens.todolist.data.model.User

interface FormContract {
    interface Presenter : BasePresenter {
        fun addNewTask(task: Task)
        fun getCurrentUser(): User?
        fun getTask(id: String): Task
        fun updateTask(task: Task)
    }

    interface View : BaseView<Presenter> {
        fun redirectToList()
        fun redirectToTaskDetail()
        fun showDatePicker()
        fun showToast(message: String)
    }
}