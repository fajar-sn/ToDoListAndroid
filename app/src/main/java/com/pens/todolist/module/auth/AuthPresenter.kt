package com.pens.todolist.module.auth

import com.pens.todolist.data.model.LoginRequest
import com.pens.todolist.data.model.LoginResponse
import com.pens.todolist.data.model.User
import com.pens.todolist.data.repository.UserSessionRepository

class AuthPresenter(private val view: AuthContract.View, private val repository: UserSessionRepository) : AuthContract.Presenter {

    override fun performLogin(loginRequest: LoginRequest) {
        val user = User(42, "Fajar Septian Nugraha", loginRequest.email, loginRequest.password)
        val response = LoginResponse(200, "TOKEN123456", user)
        repository.setSessionData(response)
        view.showToast("Login success")
        view.redirectToDashboard()
    }

    override fun performRegister() {
        TODO("Not yet implemented")
    }

    override fun start() {
        if (repository.getSessionData() != null)
            view.redirectToDashboard()
    }
    
}