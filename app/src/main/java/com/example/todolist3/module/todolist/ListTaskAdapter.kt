package com.example.todolist3.module.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist3.R
import com.example.todolist3.data.model.Task

class  ListTaskAdapter(private val listTask: ArrayList<Task>): RecyclerView.Adapter<ListTaskAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemNameTextView: TextView = itemView.findViewById(R.id.item_name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.items_list, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        var task = this.task
//        holder.itemNameTextView.text = task
        val task = listTask[position]
        holder.itemNameTextView.text = task.title
    }

    override fun getItemCount(): Int {
        return listTask.size
    }
}