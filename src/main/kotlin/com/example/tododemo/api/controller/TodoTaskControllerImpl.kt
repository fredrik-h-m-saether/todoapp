package com.example.tododemo.api.controller;

import com.example.tododemo.api.TodoTaskController
import com.example.tododemo.api.dto.TodoTaskRequest
import com.example.tododemo.api.dto.TodoTaskResponse
import com.example.tododemo.api.mapper.TodoTaskMapper
import com.example.tododemo.application.TodoTaskService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1")
class TodoTaskControllerImpl(private val todoTaskService: TodoTaskService) : TodoTaskController {
    private val logger: Logger = LoggerFactory.getLogger(TodoTaskControllerImpl::class.java)

    @GetMapping(value = ["/"])
    override fun getAllTodoTasks(): List<TodoTaskResponse> {
        logger.debug("HTTP GET /api/v1")
        return todoTaskService.getListOfTodoTasks().map {
            TodoTaskMapper.domainToTodoTaskResponse(it)
        }
    }

    @GetMapping(value = ["/todo"])
    override fun getTodoTask(@RequestParam id: UUID): TodoTaskResponse {
        logger.debug("HTTP GET /api/v1/todo - ID={}", id)
        return todoTaskService.getTodoTask(id).let {
            TodoTaskMapper.domainToTodoTaskResponse(it)
        }
    }

    @PostMapping(value = ["/todo"])
    override fun createNewTodoTask(@RequestBody todoRequest: TodoTaskRequest): TodoTaskResponse {
        logger.debug("HTTP CREATE /api/v1/todo - REQUEST_BODY={}", todoRequest)
        return todoTaskService.createNewTodoTask(todoRequest.title, todoRequest.description).let {
            TodoTaskMapper.domainToTodoTaskResponse(it)
        }
    }

    @PutMapping(value = ["/todo"])
    override fun updateExistingTodoTask(@RequestParam id: UUID, @RequestBody todoTaskRequest: TodoTaskRequest): TodoTaskResponse {
        logger.info("HTTP UPDATE /api/v1/todo - ID={}", id)
        return todoTaskService.updateExistingTodoTask(id, todoTaskRequest.title, todoTaskRequest.description).let {
            TodoTaskMapper.domainToTodoTaskResponse(it)
        }
    }

    @DeleteMapping(value = ["/todo"])
    override fun deleteExistingTodoTask(@RequestParam id: UUID): TodoTaskResponse {
        logger.debug("HTTP DELETE /api/v1/todo - ID={}", id)
        return todoTaskService.deleteExistingTodoTask(id).let {
            TodoTaskMapper.domainToTodoTaskResponse(it)
        }
    }
}
