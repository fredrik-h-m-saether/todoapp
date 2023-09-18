package com.example.tododemo.service

import com.example.tododemo.models.Todo
import com.example.tododemo.models.TodoDTO
import com.example.tododemo.repository.TodoDBRepository
import com.example.tododemo.repository.TodoPersistence
import com.sun.net.httpserver.Authenticator.Success
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull


@Service
class TodoService(val store: TodoDBRepository) {
    fun findMessages(): List<Todo> = store.findAll()

    @Transactional
    fun save(todoDTO: TodoDTO) = store.save(todoDTO.toTodo())

    fun find(id: UUID): Result<Todo> = store.findById(id).getOrNull().toResult()

    @Transactional
    fun updateStatus(id: UUID, finished: Boolean) {
        val todo = store.findById(id).getOrNull()
        todo ?: throw NotFoundException()
        store.save(todo.copy().apply { completed= finished })
    }

    @Transactional
    fun delete(id: UUID) = store.deleteById(id)
}

fun Todo?.toResult() =
    this?.let { Result.success(it) } ?: Result.failure(NotFoundException())


data class TodoNotFoundException(val id: UUID) : Exception()