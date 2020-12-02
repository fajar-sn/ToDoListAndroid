package com.example.todolist3.module.form

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.example.todolist3.R
import com.example.todolist3.base.BaseFragment
import com.example.todolist3.module.todolist.ToDoListActivity

class FormFragment : BaseFragment<FormActivity, FormContract.Presenter>(), FormContract.View, View.OnClickListener {

    private lateinit var newTaskEditText: EditText
    private lateinit var backButton: Button
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentView = inflater.inflate(R.layout.fragment_form, container, false)
        mPresenter = FormPresenter(this)
        (mPresenter as FormPresenter).start()

        newTaskEditText = fragmentView.findViewById(R.id.form_edit_text)
        backButton = fragmentView.findViewById(R.id.back_button)
        submitButton = fragmentView.findViewById(R.id.submit_button)

        backButton.setOnClickListener(this)
        submitButton.setOnClickListener(this)

        title = "Add New List"

        return fragmentView
    }

    override fun redirectToList() {
        val intent = Intent(thisActivity, ToDoListActivity::class.java)
        startActivity(intent)
        thisActivity?.finish()
    }

    override fun setPresenter(presenter: FormContract.Presenter) {
        mPresenter = presenter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.submit_button -> addNewTask()
            R.id.back_button -> redirectToList()
        }
    }

    private fun addNewTask() {
        val newTask : String = newTaskEditText.text.toString()
        val intent = Intent(thisActivity, ToDoListActivity::class.java)
        intent.putExtra(ToDoListActivity.EXTRA_TASK, newTask)
        startActivity(intent)
        thisActivity?.finish()
    }
}