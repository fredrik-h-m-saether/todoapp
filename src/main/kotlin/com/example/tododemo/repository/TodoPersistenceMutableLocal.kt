package com.example.tododemo.repository

import com.example.tododemo.service.TodoNotFoundException
import com.example.tododemo.models.Todo
import org.springframework.stereotype.Repository
import java.util.*



class TodoPersistenceMutableLocal : TodoPersistence {
    val local = mutableListOf<Todo>()

    fun saveToList(todo: Todo) {
        local.add(todo)
    }

    override fun save(todo: Todo) {
        saveToList(todo)
    }

    override fun getAll(): List<Todo> = local.toList()

    override fun find(id: UUID): Result<Todo> = local.find { e -> e.id == id }.toResult(id)

    override fun updateStatus(id: UUID, finished: Boolean): Result<Unit> {
        return this
            .find(id)
            .map { old ->
                this.delete(old.id)
                val new = old.copy().apply { completed = finished }
                saveToList(new)
            }
    }

    override fun delete(id: UUID): Result<Unit> {
        local
            .removeIf { e -> e.id == id }
            .let { found ->
                if (found)
                    return Result.success(Unit)
                else
                    return Result.failure(TodoNotFoundException(id))
            }
    }
}