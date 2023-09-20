package com.example.tododemo.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.crossstore.ChangeSetPersister
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

fun Todo?.toResult(): Result<Todo> {
    this ?: return Result.failure(ChangeSetPersister.NotFoundException())
    return Result.success(this)
}


