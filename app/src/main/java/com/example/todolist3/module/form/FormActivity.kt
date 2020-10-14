package com.example.todolist3.module.form

import android.view.View
import com.example.todolist3.base.BaseFragmentHolderActivity

class FormActivity : BaseFragmentHolderActivity() {

    private lateinit var formFragment: FormFragment

    override fun initializeFragment() {
        initializeView()

        backButton.visibility = View.VISIBLE
        optionMenuImageButton.visibility = View.GONE
        iconImageView.visibility = View.VISIBLE

        formFragment = FormFragment()
        setCurrentFragment(formFragment)
    }

}