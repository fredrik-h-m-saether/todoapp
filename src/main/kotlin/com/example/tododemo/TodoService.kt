package com.example.tododemo

import com.example.tododemo.models.Todo
import com.example.tododemo.models.TodoDTO
import org.springframework.stereotype.Service
import java.util.*


@Service
class TodoService(val store: TodoPersistence) {
    fun findMessages(): List<Todo> = store.getAll()
    fun save(todoDTO: TodoDTO) = store.save(todoDTO.toTodo())
    //TODO: Is this the right place to convert DTO to domain object?

    fun find(id: UUID): Result<Todo> = store.find(id)
    fun updateStatus(id: UUID, finished: Boolean) = store.updateStatus(id, finished)
    fun delete(id: UUID) = store.delete(id)
}


data class TodoNotFoundException(val id: UUID) : Exception()