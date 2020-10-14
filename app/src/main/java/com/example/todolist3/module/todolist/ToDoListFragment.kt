package com.example.todolist3.module.todolist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist3.R
import com.example.todolist3.base.BaseFragment
import com.example.todolist3.module.form.FormActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoListFragment : BaseFragment<ToDoListActivity, ToDoListContract.Presenter>(), ToDoListContract.View, View.OnClickListener {

    private lateinit var addNewTask: FloatingActionButton
//    private lateinit var dummyTextView: TextView
    private lateinit var taskListRecyclerView: RecyclerView
    var task: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false)
        mPresenter = ToDoListPresenter(this)
        (mPresenter as ToDoListPresenter).start()

        taskListRecyclerView = fragmentView.findViewById(R.id.items_list)
        taskListRecyclerView.setHasFixedSize(true)
        showRecyclerList()

//        dummyTextView = fragmentView.findViewById(R.id.dummy_text)
//        dummyTextView.text = task

        addNewTask = fragmentView.findViewById(R.id.add_float_action_button)
        addNewTask.setOnClickListener(this)

        title = "To Do List"

        return fragmentView
    }

    override fun redirectToFormLayout() {
        val intent = Intent(thisActivity, FormActivity::class.java)
        startActivity(intent)
        thisActivity?.finish()
    }

    override fun setPresenter(presenter: ToDoListContract.Presenter) {
        mPresenter = presenter
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.add_float_action_button -> redirectToFormLayout()
        }
    }

    private fun showRecyclerList() {
        taskListRecyclerView.layoutManager = LinearLayoutManager(thisActivity)
        val listTaskAdapter = ListTaskAdapter(task)
        taskListRecyclerView.adapter = listTaskAdapter
    }

}