package com.example.tododemo.infrastructure

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TodoTaskRepositoryImpl() : TodoTaskRepository {

    private val todoTasks: MutableList<TodoTask> = mutableListOf(
        TodoTask(UUID.fromString("984ed561-5d51-4bab-a2a9-42ee332ff2c7"), "Todo1", "A todo item", Status.CREATED),
        TodoTask(UUID.fromString("044af67d-e58b-46fb-af1b-b607a08cbf1d"), "Todo2", "A todo item", Status.IN_PROGRESS),
        TodoTask(UUID.fromString("11290a50-ad2b-4f99-96c6-9fd0f76a9edc"), "Todo3", "A todo item", Status.DONE)
    )

    @Deprecated(message = "To be removed in a future release.")
    override fun checkIfExists(id: UUID): Boolean {
        return todoTasks.any { it.id == id }
    }

    override fun findAll(): List<TodoTask> {
        return todoTasks
    }

    override fun findById(id: UUID): TodoTask? {
        return todoTasks.find { it.id == id }
    }

    override fun create(todoTask: TodoTask): TodoTask {
        todoTasks.add(todoTask)
        return todoTask
    }

    override fun update(id: UUID, todoTask: TodoTask): TodoTask {
        todoTasks.removeIf { it.id == id }
        todoTasks.add(todoTask)
        return todoTask
    }

    override fun delete(id: UUID, todoTask: TodoTask): TodoTask {
        todoTasks.removeIf { it.id == id }
        return todoTask
    }
}