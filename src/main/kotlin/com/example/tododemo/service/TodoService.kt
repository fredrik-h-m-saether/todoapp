package com.example.tododemo.service

import com.example.tododemo.models.Todo
import com.example.tododemo.models.TodoDTO
import com.example.tododemo.repository.TodoRepository
import com.example.tododemo.repository.TodoRepository.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class TodoService(val repository: TodoRepository) {
    fun findMessages(): List<Todo> = repository.getAll()

    @Transactional
    fun save(todoDTO: TodoDTO) = repository.save(todoDTO.toTodo())

    fun find(id: UUID): Todo? = repository.find(id)

    @Transactional
    @Throws(NotFoundException::class)
    fun updateStatus(id: UUID, finished: Boolean) {
        val updated =
            repository
                .find(id)
                .let { it ?: throw NotFoundException() }
                .copy()
                .apply { completed = finished }
        repository.save(updated)
    }

    @Transactional
    @Throws(NotFoundException::class, NotDeletedException::class)
    fun delete(id: UUID) = repository.delete(id)
}


