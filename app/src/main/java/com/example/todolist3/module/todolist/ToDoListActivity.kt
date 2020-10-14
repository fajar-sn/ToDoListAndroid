package com.example.todolist3.module.todolist

import android.view.View
import com.example.todolist3.base.BaseFragmentHolderActivity

class ToDoListActivity : BaseFragmentHolderActivity() {

    private lateinit var toDoListFragment: ToDoListFragment


    companion object {
        const val EXTRA_TASK = "extra_task"
    }

    override fun initializeFragment() {
        initializeView()

        backButton.visibility = View.GONE
        optionMenuImageButton.visibility = View.GONE
        iconImageView.visibility = View.VISIBLE

        toDoListFragment = ToDoListFragment()
        setCurrentFragment(toDoListFragment)

        if (intent.hasExtra(EXTRA_TASK)) {
            toDoListFragment.task = intent.getStringExtra(EXTRA_TASK).toString()
        }
    }

}