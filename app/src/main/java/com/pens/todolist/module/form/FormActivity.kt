package com.pens.todolist.module.form

import android.view.View
import com.pens.todolist.base.BaseFragmentHolderActivity

class FormActivity : BaseFragmentHolderActivity() {

    private lateinit var formFragment: FormFragment

    override fun initializeFragment() {
        initializeView()

        backButton.visibility = View.VISIBLE
        optionMenuImageButton.visibility = View.GONE
        iconImageView.visibility = View.VISIBLE

        formFragment = if (intent.getStringExtra(EXTRA_ID) == null)
            FormFragment(null)
        else
            FormFragment(intent.getStringExtra(EXTRA_ID)?.toInt())
        setCurrentFragment(formFragment)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }

}