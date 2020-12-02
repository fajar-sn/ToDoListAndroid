package com.example.todolist3.module.login

import com.example.todolist3.base.BasePresenter
import com.example.todolist3.base.BaseView
import com.example.todolist3.data.model.LoginRequest

interface LoginContract {
    interface Presenter : BasePresenter {
        fun performLogin(loginRequest : LoginRequest)
    }

    interface View : BaseView<Presenter> {
        fun redirectToDashboard()
        fun showToast(message: String)
    }
}