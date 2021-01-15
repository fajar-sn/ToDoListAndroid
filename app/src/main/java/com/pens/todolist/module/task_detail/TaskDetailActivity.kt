package com.pens.todolist.module.task_detail

import android.view.View
import com.pens.todolist.base.BaseFragmentHolderActivity

class TaskDetailActivity : BaseFragmentHolderActivity() {

    override fun initializeFragment() {
        initializeView()

        backButton.visibility = View.VISIBLE
        optionMenuImageButton.visibility = View.GONE
        iconImageView.visibility = View.GONE

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val taskDetailFragment = TaskDetailFragment(id)
        setCurrentFragment(taskDetailFragment)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }

}