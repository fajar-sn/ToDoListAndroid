package com.pens.todolist.module.form

import com.pens.todolist.data.model.Task
import com.pens.todolist.data.model.User
import com.pens.todolist.data.repository.TableHandler
import com.pens.todolist.data.repository.UserSessionRepository

class FormPresenter(
        private val view: FormContract.View,
        private val tableHandler: TableHandler<Task>,
        private val sessionRepository: UserSessionRepository
) : FormContract.Presenter {

    override fun getCurrentUser(): User? {
        return sessionRepository.getSessionData()?.user
    }

    override fun getTask(id: String) = tableHandler.readById(id)

    override fun updateTask(task: Task) {
        tableHandler.update(task)
    }

    override fun addNewTask(task: Task) {
        tableHandler.create(task)
        view.showToast("New task has been added")
        view.redirectToList()
    }

    override fun start() {}

}