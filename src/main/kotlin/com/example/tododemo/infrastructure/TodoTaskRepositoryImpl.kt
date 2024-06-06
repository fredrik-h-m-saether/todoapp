package com.example.tododemo.infrastructure

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TodoTaskRepositoryImpl() : TodoTaskRepository {

    override fun findAll(): List<TodoTask> {
        val todo1 = TodoTask(UUID.randomUUID(), "Todo1", "A todo item", Status.CREATED)
        val todo2 = TodoTask(UUID.randomUUID(), "Todo2", "A todo item", Status.IN_PROGRESS)
        val todo3 = TodoTask(UUID.randomUUID(), "Todo3", "A todo item", Status.DONE)
        return listOf(todo1, todo2, todo3)
    }

    override fun create(todoTask: TodoTask): TodoTask {
        return todoTask
    }
}