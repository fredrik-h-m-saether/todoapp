package com.example.tododemo.api.controller;

import com.example.tododemo.api.TodoTaskController
import com.example.tododemo.api.dto.TodoTaskRequest
import com.example.tododemo.api.dto.TodoTaskResponse
import com.example.tododemo.api.mapper.TodoTaskMapper
import com.example.tododemo.application.TodoTaskService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TodoTaskControllerImpl(private val todoTaskService: TodoTaskService) : TodoTaskController {
    private val LOGGER: Logger = LoggerFactory.getLogger(TodoTaskControllerImpl::class.java)

    @GetMapping(value = ["/v1/todo"])
    override fun getListOfTodoTasks(): List<TodoTaskResponse> {
        LOGGER.debug("HTTP GET /api/v1/todo")
        return todoTaskService.getListOfTodoTasks().map {
            TodoTaskMapper.domainToTodoResponse(it)
        }
    }

    @PostMapping(value = ["/v1/todo"])
    override fun createNewTodoTask(@RequestBody todoRequest: TodoTaskRequest): TodoTaskResponse {
        LOGGER.debug("HTTP CREATE /api/v1/todo - REQUEST_BODY={}", todoRequest)
        return todoTaskService.createNewTodoTask(todoRequest.title, todoRequest.description).let {
            TodoTaskMapper.domainToTodoResponse(it)
        }
    }
}
