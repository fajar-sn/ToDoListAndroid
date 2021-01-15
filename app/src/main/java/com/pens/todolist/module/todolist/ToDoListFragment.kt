package com.pens.todolist.module.todolist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pens.todolist.R
import com.pens.todolist.base.BaseFragment
import com.pens.todolist.module.form.FormActivity
import com.pens.todolist.utils.ListTaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pens.todolist.data.repository.TaskTableHandler

class ToDoListFragment : BaseFragment<ToDoListActivity, ToDoListContract.Presenter>(), ToDoListContract.View, View.OnClickListener {

    private lateinit var addNewTask: FloatingActionButton
    private lateinit var taskListRecyclerView: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentView = inflater.inflate(R.layout.fragment_list, container, false)
        mPresenter = ToDoListPresenter(TaskTableHandler(activity))
        (mPresenter as ToDoListPresenter).start()

        taskListRecyclerView = fragmentView.findViewById(R.id.items_list)
        taskListRecyclerView.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(thisActivity)
        taskListRecyclerView.layoutManager = mLayoutManager

        val data = (mPresenter as ToDoListPresenter).getDataSet()
        taskListRecyclerView.adapter = data?.let { ListTaskAdapter(it, this) }

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

}