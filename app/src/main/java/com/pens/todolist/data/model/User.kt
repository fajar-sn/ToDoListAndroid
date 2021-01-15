package com.pens.todolist.data.model

data class User(
    var name: String,
    var email: String,
    var password: String
) {
    constructor(
        id: Int?,
        name: String,
        email: String,
        password: String
    ): this(name, email, password) {
        this.id = id
    }

    var id: Int? = 0
}