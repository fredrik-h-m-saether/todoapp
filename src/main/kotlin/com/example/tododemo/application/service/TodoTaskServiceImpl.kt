package com.example.tododemo.application.service

import com.example.tododemo.application.TodoTaskService
import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.exceptions.TodoTaskNotFoundException
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoTaskServiceImpl(val todoTaskRepository: TodoTaskRepository) : TodoTaskService {
    private val logger: Logger = LoggerFactory.getLogger(TodoTaskServiceImpl::class.java)

    override fun getListOfTodoTasks(): List<TodoTask> {
        logger.debug("Retrieving a list of all Todo tasks")
        return todoTaskRepository.findAll()
    }

    override fun getTodoTask(id: UUID): TodoTask {
        logger.debug("Retrieving a Todo task with id={}", id)
        return todoTaskRepository.findById(id)
            ?: throw TodoTaskNotFoundException("Todo task with id=$id not found")
    }

    override fun createNewTodoTask(title: String?, description: String?): TodoTask {
        val id: UUID = UUID.randomUUID()
        logger.debug("Creating a new Todo task with id={}", id)
        val newTodoTask = TodoTask(id, title, description, Status.CREATED)
        todoTaskRepository.create(newTodoTask)
        return newTodoTask
    }

    override fun updateExistingTodoTask(id: UUID, title: String?, description: String?): TodoTask {
        logger.debug("Updating an existing Todo task with id={}", id)
        val existingTodoTask = todoTaskRepository.findById(id)
            ?: throw TodoTaskNotFoundException("Todo task with id=$id not found")
        val todoTaskToBeUpdated = existingTodoTask.updateTodoTask(title, description)
        return todoTaskRepository.update(id, todoTaskToBeUpdated)
    }

    override fun deleteExistingTodoTask(id: UUID): TodoTask {
        logger.debug("Deleting an existing Todo task associated by id={}", id)
        val todoTaskToBeDeleted: TodoTask = todoTaskRepository.findById(id)
            ?: throw TodoTaskNotFoundException("Todo task with id=$id not found")
        return todoTaskRepository.delete(id, todoTaskToBeDeleted)
    }
}