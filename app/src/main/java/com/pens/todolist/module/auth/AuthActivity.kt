package com.pens.todolist.module.auth

import android.view.View
import com.pens.todolist.base.BaseFragmentHolderActivity

class AuthActivity : BaseFragmentHolderActivity() {

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