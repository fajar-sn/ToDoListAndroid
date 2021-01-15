package com.pens.todolist.module.form

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pens.todolist.R
import com.pens.todolist.base.BaseFragment
import com.pens.todolist.data.model.Task
import com.pens.todolist.data.repository.TaskTableHandler
import com.pens.todolist.data.repository.UserSessionRepository
import com.pens.todolist.module.task_detail.TaskDetailActivity
import com.pens.todolist.module.todolist.ToDoListActivity
import java.util.*


class FormFragment(private val id: Int?) : BaseFragment<FormActivity, FormContract.Presenter>(), FormContract.View, View.OnClickListener {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var task: Task

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentView = inflater.inflate(R.layout.fragment_form, container, false)
        mPresenter = FormPresenter(this, TaskTableHandler(activity), UserSessionRepository(activity))
        (mPresenter as FormPresenter).start()

        titleEditText = fragmentView.findViewById(R.id.title_edit_text)
        descriptionEditText = fragmentView.findViewById(R.id.description_edit_text)
        dateEditText = fragmentView.findViewById(R.id.date_edit_text)
        submitButton = fragmentView.findViewById(R.id.submit_button)

        dateEditText.setOnClickListener(this)
        submitButton.setOnClickListener(this)

        title = if (id != null) {
            task = (mPresenter as FormPresenter).getTask(id.toString())
            titleEditText.text.append(task.title)
            descriptionEditText.text.append(task.description)
            dateEditText.text.append(task.date)
            "Edit Task"
        } else
            "Add New Task"

        return fragmentView
    }

    override fun redirectToList() {
        val intent = Intent(thisActivity, ToDoListActivity::class.java)
        startActivity(intent)
        thisActivity?.finish()
    }

    override fun redirectToTaskDetail() {
        val intent = Intent(thisActivity, TaskDetailActivity::class.java)
        intent.putExtra(TaskDetailActivity.EXTRA_ID, id)
        startActivity(intent)
        thisActivity?.finish()
    }

    override fun setPresenter(presenter: FormContract.Presenter) {
        mPresenter = presenter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.submit_button -> if (id == null) addNewTask() else editTask()
            R.id.date_edit_text -> {
                showDatePicker()
            }
        }
    }

    override fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val thisYear = calendar.get(Calendar.YEAR)
        val thisMonth = calendar.get(Calendar.MONTH)
        val today = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = thisActivity?.let {
            DatePickerDialog(
                it,
                { view, year, monthOfYear, dayOfMonth -> dateEditText.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                thisYear,
                thisMonth,
                today
            )
        }

        datePickerDialog?.show()
    }

    override fun showToast(message: String) {
        Toast.makeText(thisActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun addNewTask() {
        val title = titleEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()
        val date = dateEditText.text.toString().trim()
        val isDone = 0
        val userId = mPresenter?.getCurrentUser()?.id

        val newTask = userId?.let { Task(title, description, date, isDone, it) }
        newTask?.let { mPresenter?.addNewTask(it) }
    }

    private fun editTask() {
        task.title = titleEditText.text.toString().trim()
        task.description = descriptionEditText.text.toString().trim()
        task.date = dateEditText.text.toString().trim()
        mPresenter?.updateTask(task)
        showToast("Task Edited")
        redirectToTaskDetail()
    }
}