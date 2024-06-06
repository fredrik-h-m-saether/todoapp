package com.example.tododemo.unit.infrastructure

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import com.example.tododemo.infrastructure.TodoTaskRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class TodoTaskRepositoryTest {

    private lateinit var todoTaskRepository: TodoTaskRepository

    @BeforeEach
    fun setup() {
        todoTaskRepository = TodoTaskRepositoryImpl()
    }

    @Test
    fun `given TodoTaskRepository, when calling findAll, assert success`() {
        // given && when
        val todos: List<TodoTask> = todoTaskRepository.findAll()

        // then
        assertNotNull(todos)
        assertEquals(3, todos.size)
    }

    @Test
    fun `given TodoTaskRepository, when calling create, assert success`() {
        // given
        val uuid = UUID.randomUUID()
        val todoTask = TodoTask(uuid, "Todo1", "A todo item", Status.CREATED)

        // when
        val actualTodoTask: TodoTask = todoTaskRepository.create(todoTask)

        // then
        assertEquals(uuid, actualTodoTask.id)
        assertEquals("Todo1", actualTodoTask.title)
        assertEquals("A todo item", actualTodoTask.description)
        assertEquals(Status.CREATED, actualTodoTask.status)
    }
}