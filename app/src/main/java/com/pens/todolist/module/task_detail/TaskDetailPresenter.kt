package com.pens.todolist.module.task_detail

import com.pens.todolist.data.model.Task
import com.pens.todolist.data.repository.TableHandler

class TaskDetailPresenter(private val tableHandler: TableHandler<Task>): TaskDetailContract.Presenter {

    override fun getTask(id: Int) = tableHandler.readById(id.toString())

    override fun updateTask(task: Task) {
        tableHandler.update(task)
    }

    override fun deleteTask(task: Task) {
        tableHandler.delete(task)
    }

    override fun start() {}

}