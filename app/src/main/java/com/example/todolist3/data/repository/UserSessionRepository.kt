package com.example.todolist3.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.example.todolist3.data.model.LoginResponse
import com.example.todolist3.data.model.User
import com.google.gson.Gson

class UserSessionRepository(context: FragmentActivity?){

    companion object {
        private val SESSION_USER = "SessionUser"
    }

    val SHARED_PREFERENCES_NAME = "SessionSharedPreferences"
    private lateinit var sharedPreference: SharedPreferences

    init {
        if (context != null) {
            sharedPreference = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        }
    }

    fun initialize(sessionData: LoginResponse): LoginResponse? {
        setSessionData(sessionData)
        return getSessionData()
    }

    fun setSessionData(sessionData: LoginResponse) {
        val editor = sharedPreference.edit()
        editor.putString(SESSION_USER, Gson().toJson(sessionData))
        editor.apply()
    }

    fun getSessionData() : LoginResponse? {
        val sessionDataJson = sharedPreference.getString(SESSION_USER, null)
        if (sessionDataJson != null)
            return Gson().fromJson(sessionDataJson, LoginResponse::class.java)
        return null
    }

    fun destroy() {
        sharedPreference.edit().clear().apply()
    }

    fun update(user: User) {
        destroy()
        val sessionData = getSessionData()
        if (sessionData != null) {
            sessionData.user = user
            setSessionData(sessionData)
        }
    }

}