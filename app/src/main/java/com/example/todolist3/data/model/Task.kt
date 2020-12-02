package com.example.todolist3.data.model

import java.time.LocalDate
import java.time.LocalTime

data class Task (
    val id : Int,
    val title : String,
    val description: String,
    val date: String,
    val time: String
)