package com.pens.todolist.module.task_detail

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import com.pens.todolist.base.BasePresenter
import com.pens.todolist.base.BaseView
import com.pens.todolist.data.model.Task

interface TaskDetailContract {

    interface Presenter: BasePresenter {
        fun getTask(id: Int): Task
        fun updateTask(task: Task)
        fun deleteTask(task: Task)
    }

    interface View: BaseView<Presenter> {
        fun redirectToList()
        fun redirectToFormLayout()
        fun setTextView()
        fun initializeBuilder()
        fun shareTask()
    }

}