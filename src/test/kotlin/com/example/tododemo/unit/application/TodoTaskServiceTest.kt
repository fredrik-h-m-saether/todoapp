package com.example.tododemo.unit.application

import com.example.tododemo.application.TodoTaskService
import com.example.tododemo.application.service.TodoTaskServiceImpl
import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import java.util.*
import kotlin.collections.List

class TodoTaskServiceTest {

    private lateinit var todoTaskRepository: TodoTaskRepository
    private lateinit var todoTaskService: TodoTaskService

    @BeforeEach
    fun setup() {
        todoTaskRepository = Mockito.mock(TodoTaskRepository::class.java)
        todoTaskService = TodoTaskServiceImpl(todoTaskRepository)
    }

    @Test
    fun `given TodoTaskRepository returns valid values, when calling getListOfTodoTasks, assert success`() {
        // given
        val todo1 = TodoTask(UUID.randomUUID(), "Todo1", "A todo item", Status.CREATED)
        val todo2 = TodoTask(UUID.randomUUID(), "Todo2", "A todo item", Status.IN_PROGRESS)
        val todo3 = TodoTask(UUID.randomUUID(), "Todo3", "A todo item", Status.DONE)
        Mockito.`when`(todoTaskRepository.findAll())
            .thenReturn(listOf(todo1, todo2, todo3))

        // when
        val todoTasks: List<TodoTask> = todoTaskService.getListOfTodoTasks()

        // then
        assertNotNull(todoTasks)
        assertEquals(listOf(todo1, todo2, todo3), todoTasks)
    }

    @Test
    fun `given TodoTaskRepository returns valid value, when calling createNewTodoTask, assert success`() {
        // given
        val title = "Todo1"
        val description = "A todo item"

        val todoTask = TodoTask(UUID.randomUUID(), "Todo1", "A todo item", Status.CREATED)
        Mockito.`when`(todoTaskRepository.create(any()))

            .thenReturn(todoTask)

        // when
        val actualTodoTask: TodoTask = todoTaskService.createNewTodoTask(title, description)

        // then
        println(actualTodoTask)
    }
}