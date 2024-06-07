package com.example.tododemo.unit.application

import com.example.tododemo.application.TodoTaskService
import com.example.tododemo.application.service.TodoTaskServiceImpl
import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.exceptions.TodoTaskNotFoundException
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
    fun `tests getListOfTodoTasks`() {
        // given
        val todo1 = TodoTask(UUID.randomUUID(), "Todo1", "A todo item", Status.READY)
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
    fun `tests getTodoTask`() {
        // given
        val id: UUID = UUID.randomUUID()
        val todoTask = TodoTask(id, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(todoTaskRepository.findById(id))
            .thenReturn(todoTask)

        // when
        val actualTodoTask: TodoTask = todoTaskService.getTodoTask(id)

        // then
        assertNotNull(actualTodoTask)
        assertEquals(todoTask, actualTodoTask)
    }

    @Test
    fun `tests getTodoTask, but Todo task is not found`() {
        // given
        val id: UUID = UUID.randomUUID()
        val todoTask = TodoTask(id, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(todoTaskRepository.findById(id))
            .thenReturn(null)

        // when
        val exception: TodoTaskNotFoundException = assertThrows {
            todoTaskService.getTodoTask(id)
        }

        // then
        assertNotNull(todoTask)
        assertEquals("Todo task with id=$id not found", exception.message)
    }

    @Test
    fun `tests createNewTodoTask`() {
        // given
        val title = "Todo1"
        val description = "A todo item"

        val todoTask = TodoTask(UUID.randomUUID(), "Todo1", "A todo item", Status.READY)
        Mockito.`when`(todoTaskRepository.create(any()))
            .thenReturn(todoTask)

        // when
        val actualTodoTask: TodoTask = todoTaskService.createNewTodoTask(title, description)

        // then
        assertNotNull(actualTodoTask)
        assertEquals(title, actualTodoTask.title)
        assertEquals(description, actualTodoTask.description)
        assertEquals(Status.CREATED, actualTodoTask.status)
    }

    @Test
    fun `tests updateExistingTodoTask`() {
        // given
        val id: UUID = UUID.randomUUID()
        val title = "Todo1"
        val description = "A todo item"
        val todoTask = TodoTask(id, title, description, Status.UPDATED)
        Mockito.`when`(todoTaskRepository.findById(id))
            .thenReturn(todoTask)
        Mockito.`when`(todoTaskRepository.update(id, todoTask))
            .thenReturn(todoTask)

        // when
        val actualTodoTask: TodoTask = todoTaskService.updateExistingTodoTask(id, title, description)

        // then
        assertNotNull(actualTodoTask)
        assertEquals(todoTask, actualTodoTask)
    }

    @Test
    fun `tests updateExistingTodoTask, but Todo task is not found`() {
        // given
        val id: UUID = UUID.randomUUID()
        Mockito.`when`(todoTaskRepository.findById(id))
            .thenReturn(null)

        // when
        val exception: TodoTaskNotFoundException = assertThrows {
            todoTaskService.updateExistingTodoTask(id, null, null)
        }

        // then
        assertEquals("Todo task with id=$id not found", exception.message)
    }

    @Test
    fun `tests deleteExistingTodoTask`() {
        // given
        val id: UUID = UUID.randomUUID()
        val title = "Todo1"
        val description = "A todo item"
        val todoTask = TodoTask(id, title, description, Status.UPDATED)
        Mockito.`when`(todoTaskRepository.findById(id))
            .thenReturn(todoTask)
        Mockito.`when`(todoTaskRepository.delete(id, todoTask))
            .thenReturn(todoTask)

        // when
        val actualTodoTask: TodoTask = todoTaskService.deleteExistingTodoTask(id)

        // then
        assertNotNull(actualTodoTask)
        assertEquals(todoTask, actualTodoTask)
    }

    @Test
    fun `tests deleteExistingTodoTask, but Todo task is not found`() {
        // given
        val id: UUID = UUID.randomUUID()
        Mockito.`when`(todoTaskRepository.findById(id))
            .thenReturn(null)

        // when
        val exception: TodoTaskNotFoundException = assertThrows {
            todoTaskService.deleteExistingTodoTask(id)
        }

        // then
        assertEquals("Todo task with id=$id not found", exception.message)
    }
}