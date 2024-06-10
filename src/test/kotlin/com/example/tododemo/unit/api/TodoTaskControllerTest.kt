package com.example.tododemo.unit.api

import com.example.tododemo.api.TodoTaskController
import com.example.tododemo.api.controller.TodoTaskControllerImpl
import com.example.tododemo.api.dto.TodoTaskRequest
import com.example.tododemo.api.dto.TodoTaskResponse
import com.example.tododemo.application.TodoTaskService
import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class TodoTaskControllerTest {

    private lateinit var todoTaskController: TodoTaskController
    private lateinit var todoTaskService: TodoTaskService

    @BeforeEach
    fun setup() {
        todoTaskService = Mockito.mock(TodoTaskService::class.java)
        todoTaskController = TodoTaskControllerImpl(todoTaskService)
    }

    @Test
    fun `tests getAllTodoTasks`() {
        // given
        val uuid = UUID.randomUUID()
        val todo = TodoTask(uuid, "Todo1", "A todo item", Status.CREATED)
        Mockito.`when`(todoTaskService.getListOfTodoTasks())
            .thenReturn(listOf(todo))

        // when
        val todoTasks: List<TodoTaskResponse> = todoTaskController.getAllTodoTasks()

        // then
        assertNotNull(todoTasks)
        assertEquals(uuid, todoTasks.first().id)
    }

    @Test
    fun `tests getTodoTask`() {
        // given
        val id = UUID.randomUUID()
        val todo = TodoTask(id, "Todo1", "A todo item", Status.CREATED)
        Mockito.`when`(todoTaskService.getTodoTask(id))
            .thenReturn(todo)

        // when
        val todoTaskResponse: TodoTaskResponse = todoTaskController.getTodoTask(id)

        // then
        assertNotNull(todoTaskResponse)
        assertEquals(id, todoTaskResponse.id)
    }

    @Test
    fun `tests createNewTodoTask`() {
        // given
        val todoTaskRequest = TodoTaskRequest("Todo1", "A todo item")

        val id = UUID.randomUUID()
        val todoTask = TodoTask(id, "Todo1", "A todo item", Status.CREATED)
        Mockito.`when`(todoTaskService.createNewTodoTask(todoTaskRequest.title, todoTaskRequest.description))
            .thenReturn(todoTask)

        // when
        val todoTaskResponse: TodoTaskResponse = todoTaskController.createNewTodoTask(todoTaskRequest)

        // then
        assertNotNull(todoTaskResponse)
        assertEquals(id, todoTaskResponse.id)
    }

    @Test
    fun `tests updateExistingTodoTask`() {
        // given
        val todoTaskRequest = TodoTaskRequest("Todo1", "A todo item", Status.UPDATED)

        val id = UUID.randomUUID()
        val todoTask = TodoTask(id, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(todoTaskService.updateExistingTodoTask(id, todoTaskRequest.title, todoTaskRequest.description, todoTaskRequest.status))
            .thenReturn(todoTask)

        // when
        val todoTaskResponse: TodoTaskResponse = todoTaskController.updateExistingTodoTask(id, todoTaskRequest)

        // then
        assertNotNull(todoTaskResponse)
        assertEquals(id, todoTaskResponse.id)
    }

    @Test
    fun `tests deleteExistingTodoTask`() {
        // given
        val id = UUID.randomUUID()
        val todoTask = TodoTask(id, "Todo1", "A todo item", Status.UPDATED)
        Mockito.`when`(todoTaskService.deleteExistingTodoTask(id))
            .thenReturn(todoTask)

        // when
        val todoTaskResponse: TodoTaskResponse = todoTaskController.deleteExistingTodoTask(id)

        // then
        println(todoTaskResponse)
        assertNotNull(todoTaskResponse)
        assertEquals(id, todoTaskResponse.id)
    }
}