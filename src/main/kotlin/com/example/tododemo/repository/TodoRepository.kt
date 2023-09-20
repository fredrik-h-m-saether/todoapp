package com.example.tododemo.repository

import com.example.tododemo.models.Todo
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.transaction.annotation.Transactional
import java.util.*


interface TodoRepository {
    fun save(todo: Todo): Todo
    fun getAll(): List<Todo>
    fun find(id: UUID): Todo?

    @Transactional
    @Throws(ChangeSetPersister.NotFoundException::class, NotUpdatedException::class)
    fun updateStatus(id: UUID, finished: Boolean) : Todo


    @Throws(ChangeSetPersister.NotFoundException::class, NotDeletedException::class)
    fun delete(id: UUID)


    class NotDeletedException : Exception()
    class NotUpdatedException : Exception()
}



