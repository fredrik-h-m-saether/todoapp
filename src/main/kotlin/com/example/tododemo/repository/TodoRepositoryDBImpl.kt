package com.example.tododemo.repository

import com.example.tododemo.models.Todo
import com.example.tododemo.repository.TodoRepository.NotDeletedException
import com.example.tododemo.repository.TodoRepository.NotUpdatedException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrNull


@Repository
class TodoRepositoryDBImpl(val db: TodoJpaRepository) : TodoRepository {

    override fun save(todo: Todo): Todo = db.save(todo)

    override fun getAll(): List<Todo> = db.findAll()

    @Transactional
    @Throws(NotFoundException::class, NotUpdatedException::class)
    override fun updateStatus(id: UUID, finished: Boolean): Todo =
        this
            .find(id)
            .let { it ?: throw NotFoundException() }
            .let { old ->
                try {
                    this.delete(old.id)
                } catch (e: NotDeletedException) {
                    throw NotUpdatedException()
                }
                val new = old.copy().apply { completed = finished }
                save(new)
            }

    override fun find(id: UUID): Todo? = db.findById(id).getOrNull()

    @Transactional
    @Throws(NotFoundException::class, NotDeletedException::class)
    override fun delete(id: UUID) {
        val exist = this.find(id) ?: throw NotFoundException()
        db.deleteById(id)
        if (exist == this.find(id))
            throw NotDeletedException()

    }

}


