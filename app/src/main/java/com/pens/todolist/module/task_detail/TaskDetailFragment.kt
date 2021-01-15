package com.pens.todolist.module.task_detail

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.pens.todolist.R
import com.pens.todolist.base.BaseFragment
import com.pens.todolist.data.model.Task
import com.pens.todolist.data.repository.TaskTableHandler
import com.pens.todolist.module.form.FormActivity
import com.pens.todolist.module.todolist.ToDoListActivity

class TaskDetailFragment(private val id: Number):
        BaseFragment<TaskDetailActivity, TaskDetailContract.Presenter>(),
        TaskDetailContract.View,
        View.OnClickListener {

    private lateinit var titleTextView: AppCompatTextView
    private lateinit var descriptionTextView: AppCompatTextView
    private lateinit var dateTextView: AppCompatTextView
    private lateinit var isDoneTextView: AppCompatTextView
    private lateinit var editButton: AppCompatButton
    private lateinit var deleteButton: AppCompatButton
    private lateinit var shareButton: AppCompatButton
    private var alert: AlertDialog? = null
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        fragmentView = inflater.inflate(R.layout.activity_task_detail, container, false)
        mPresenter = TaskDetailPresenter(TaskTableHandler(activity))
        (mPresenter as TaskDetailPresenter).start()

        titleTextView = fragmentView.findViewById(R.id.task_title_text_view)
        descriptionTextView = fragmentView.findViewById(R.id.task_description_text_view)
        dateTextView = fragmentView.findViewById(R.id.task_date_text_view)
        isDoneTextView = fragmentView.findViewById(R.id.task_is_done_text_view)
        editButton = fragmentView.findViewById(R.id.edit_task_button)
        deleteButton = fragmentView.findViewById(R.id.delete_task_button)
        shareButton = fragmentView.findViewById(R.id.share_task_button)

        task = mPresenter?.getTask(id as Int)
        title = task?.title.toString()

        editButton.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
        shareButton.setOnClickListener(this)

        setTextView()
        initializeBuilder()

        return fragmentView
    }

    override fun redirectToList() {
        val intent = Intent(thisActivity, ToDoListActivity::class.java)
        startActivity(intent)
        thisActivity?.finish()
    }

    override fun redirectToFormLayout() {
        val intent = Intent(thisActivity, FormActivity::class.java)
        intent.putExtra(FormActivity.EXTRA_ID, id.toString())
        startActivity(intent)
    }

    override fun setPresenter(presenter: TaskDetailContract.Presenter) {
        mPresenter = presenter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.edit_task_button -> redirectToFormLayout()
            R.id.delete_task_button -> alert?.show()
            R.id.share_task_button -> shareTask()
        }
    }

    override fun setTextView() {
        titleTextView.text = task?.title
        descriptionTextView.text = task?.description
        dateTextView.text = task?.date
        isDoneTextView.text = if (task?.isDone == 1) "Done" else "Not done yet"
    }

    override fun initializeBuilder() {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Delete task")
        builder?.setMessage("Are you sure to delete this task?")
        builder?.setPositiveButton("Yes") { dialogInterface, _ ->
            dialogInterface.dismiss()
            task?.let { mPresenter?.deleteTask(it) }
            Toast.makeText(context, "Task deleted", Toast.LENGTH_SHORT).show()
            redirectToList()
        }
        builder?.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alert = builder?.create()
    }

    override fun shareTask() {
        val sendMessage =
            "${task?.title}\n\n" +
            "${task?.description}\n\n" +
            "${task?.date}"

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, sendMessage)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}