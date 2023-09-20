package com.example.tododemo.models

import java.util.*

data class TodoDTO(val title: String, val description: String) {

    fun toTodo() = Todo().let {
        it.id = UUID.randomUUID()
        it.title = title
        it.description = description
        it.completed = false
        it
    }
}

