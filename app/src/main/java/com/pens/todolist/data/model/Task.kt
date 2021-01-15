package com.pens.todolist.data.model

data class Task (
    var title : String,
    var description: String,
    var date: String,
    var isDone: Int,
    val userId: Int
) {

    var id: Int? = 0

    constructor(
        id: Int?,
        title: String,
        description: String,
        date: String,
        isDone: Int,
        userId: Int
    ): this(title, description, date, isDone, userId) {
        this.id = id
    }
}