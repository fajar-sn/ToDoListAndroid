package com.pens.todolist.module.todolist

import com.pens.todolist.data.model.Task
import com.pens.todolist.data.repository.TaskTableHandler

class ToDoListPresenter(private val tableHandler: TaskTableHandler?) : ToDoListContract.Presenter {

    override fun getDataSet() = tableHandler?.readAll()

    override fun update(task: Task) {
        tableHandler?.update(task)
    }

    override fun getData(id: Int) = tableHandler?.readById(id.toString())

    override fun start() {}

}