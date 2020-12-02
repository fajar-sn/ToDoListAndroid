package com.example.todolist3.module.login

import com.example.todolist3.data.model.LoginRequest
import com.example.todolist3.data.model.LoginResponse
import com.example.todolist3.data.model.User
import com.example.todolist3.data.repository.UserSessionRepository

class LoginPresenter(private val view: LoginContract.View, private val repository: UserSessionRepository) : LoginContract.Presenter {

    override fun performLogin(loginRequest: LoginRequest) {
        val user = User(42, "Fajar", "Septian Nugraha", loginRequest.email)
        val response = LoginResponse(200, "TOKEN123456", user)
        repository.setSessionData(response)
        view.showToast("Login success")
        view.redirectToDashboard()
    }

    override fun start() {
        if (repository.getSessionData() != null)
            view.redirectToDashboard()
    }
    
}