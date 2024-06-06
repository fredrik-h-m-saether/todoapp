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
    fun `given TodoTaskService returns valid values, when calling getListOfTodoTasks, assert success`() {
        // given
        val uuid = UUID.randomUUID()
        val todo1 = TodoTask(uuid, "Todo1", "A todo item", Status.CREATED)
        Mockito.`when`(todoTaskService.getListOfTodoTasks())
            .thenReturn(listOf(todo1))

        // when
        val todoTasks: List<TodoTaskResponse> = todoTaskController.getListOfTodoTasks()

        // then
        assertNotNull(todoTasks)
        assertEquals(uuid, todoTasks.first().id)
        assertEquals("Todo1", todoTasks.first().title)
        assertEquals("A todo item", todoTasks.first().description)
        assertEquals(Status.CREATED, todoTasks.first().status)
    }

    @Test
    fun `given TodoTaskService returns valid value, when calling getListOfTodoTasks, assert success`() {
        // given
        val todoTaskRequest = TodoTaskRequest("Todo1", "A todo item")

        val uuid = UUID.randomUUID()
        val todoTask = TodoTask(uuid, "Todo1", "A todo item", Status.CREATED)
        Mockito.`when`(todoTaskService.createNewTodoTask(todoTaskRequest.title, todoTaskRequest.description))
            .thenReturn(todoTask)

        // when
        val actualTodoTask: TodoTaskResponse = todoTaskController.createNewTodoTask(todoTaskRequest)

        // then
        assertEquals(uuid, actualTodoTask.id)
        assertEquals("Todo1", actualTodoTask.title)
        assertEquals("A todo item", actualTodoTask.description)
        assertEquals(Status.CREATED, actualTodoTask.status)
    }
}