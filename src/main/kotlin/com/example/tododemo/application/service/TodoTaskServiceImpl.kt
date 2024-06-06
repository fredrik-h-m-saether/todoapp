package com.example.tododemo.application.service

import com.example.tododemo.application.TodoTaskService
import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoTaskServiceImpl(val todoTaskRepository: TodoTaskRepository) : TodoTaskService {

    override fun getListOfTodoTasks(): List<TodoTask> {
        return todoTaskRepository.findAll()
    }

    override fun createNewTodoTask(title: String, description: String): TodoTask {
        val newUUID: UUID = UUID.randomUUID()
        val newTodoTask = TodoTask(newUUID, "Todo1", "A todo item", Status.CREATED)
        return todoTaskRepository.create(newTodoTask)
    }
}