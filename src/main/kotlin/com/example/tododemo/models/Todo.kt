package com.example.tododemo.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*


@Entity
class Todo {
    @Id
    lateinit var id: UUID
    lateinit var title: String
    lateinit var description: String
    var completed: Boolean = false

    fun copy() = Todo().let {
        it.id = id
        it.title = title
        it.description = description
        it.completed = completed
        it
    }
}


