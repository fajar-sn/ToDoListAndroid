package com.pens.todolist.module.todolist

import android.view.View
import com.pens.todolist.base.BaseFragmentHolderActivity

class ToDoListActivity : BaseFragmentHolderActivity() {

    private lateinit var toDoListFragment: ToDoListFragment

    override fun initializeFragment() {
        initializeView()

        backButton.visibility = View.GONE
        optionMenuImageButton.visibility = View.GONE
        iconImageView.visibility = View.VISIBLE

        toDoListFragment = ToDoListFragment()
        setCurrentFragment(toDoListFragment)

    }

}