package com.pens.todolist.module.auth

import com.pens.todolist.base.BasePresenter
import com.pens.todolist.base.BaseView
import com.pens.todolist.data.model.LoginRequest

interface AuthContract {
    interface Presenter : BasePresenter {
        fun performLogin(loginRequest : LoginRequest)
        fun performRegister()
    }

    interface View : BaseView<Presenter> {
        fun redirectToDashboard()
        fun showToast(message: String)
    }
}