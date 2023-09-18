package com.example.tododemo.repository

import com.example.tododemo.service.TodoNotFoundException
import com.example.tododemo.models.Todo
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*


interface TodoPersistence {
    fun save(todo: Todo)
    fun getAll(): List<Todo>
    fun find(id: UUID): Result<Todo>
    fun updateStatus(id: UUID, finished: Boolean): Result<Unit>
    fun delete(id: UUID): Result<Unit>
}


fun Todo?.toResult(id: UUID): Result<Todo> {
    this ?: return Result.failure(NotFoundException())
    return Result.success(this)
}

