package com.example.tododemo.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.context.annotation.Primary
import java.util.*


@Entity
class Todo {
    @Id
    lateinit var id: UUID
    lateinit var title: String
    lateinit var description: String
    var completed: Boolean = false

    fun copy() = Todo().apply {
        id = id
        title = title
        description = description
        completed = completed
    }
}