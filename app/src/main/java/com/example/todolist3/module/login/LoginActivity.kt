package com.example.todolist3.module.login

import android.view.View
import com.example.todolist3.base.BaseFragmentHolderActivity

class LoginActivity : BaseFragmentHolderActivity() {

    private lateinit var loginFragment: LoginFragment

    override fun initializeFragment() {
        initializeView()

        backButton.visibility = View.GONE
        optionMenuImageButton.visibility = View.GONE
        iconImageView.visibility = View.VISIBLE

        loginFragment = LoginFragment()
        setCurrentFragment(loginFragment)
    }
}