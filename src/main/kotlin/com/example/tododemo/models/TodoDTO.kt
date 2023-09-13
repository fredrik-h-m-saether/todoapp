package com.example.tododemo.models

import java.util.*

data class TodoDTO(val title: String, val description: String) {

    fun toTodo() = Todo(
        id = UUID.randomUUID(),
        title = title,
        description = description,
        finished = false
    )
}

