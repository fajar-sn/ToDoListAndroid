package com.pens.todolist.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.pens.todolist.R
import com.pens.todolist.base.BaseActivity
import com.pens.todolist.base.BaseFragment
import com.pens.todolist.data.model.Task
import com.pens.todolist.module.task_detail.TaskDetailActivity
import com.pens.todolist.module.todolist.ToDoListActivity
import com.pens.todolist.module.todolist.ToDoListContract
import com.pens.todolist.module.todolist.ToDoListFragment

class ListTaskAdapter(
    private val listTask: List<Task>,
    private val fragment: BaseFragment<ToDoListActivity, ToDoListContract.Presenter>
): RecyclerView.Adapter<ListTaskAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.items_list, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val task = listTask[position]
        holder.itemNameTextView.text = task.title
        holder.itemCheckBox.isChecked = task.isDone == 1

        holder.itemView.setOnClickListener {
            val intent = Intent(fragment.thisActivity, TaskDetailActivity::class.java)
            intent.putExtra(TaskDetailActivity.EXTRA_ID, task.id)
            fragment.startActivity(intent)
        }

        holder.itemCheckBox.setOnCheckedChangeListener { buttonView, _ ->
            task.isDone = if (buttonView.isChecked) 1 else 0
            fragment.mPresenter?.update(task)
        }

    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemNameTextView: TextView = itemView.findViewById(R.id.item_name)
        var itemCheckBox: MaterialCheckBox = itemView.findViewById(R.id.item_checkbox)
    }
}