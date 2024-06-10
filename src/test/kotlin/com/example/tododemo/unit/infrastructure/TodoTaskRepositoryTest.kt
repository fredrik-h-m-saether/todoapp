package com.example.tododemo.unit.infrastructure

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.exceptions.TodoTaskNotFoundException
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import com.example.tododemo.infrastructure.mapper.TodoTaskRowMapper
import com.example.tododemo.infrastructure.persistence.TodoTaskRepositoryImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.kotlin.eq
import org.mockito.kotlin.isA
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*

class TodoTaskRepositoryTest {
    private lateinit var jdbcTemplate: JdbcTemplate
    private lateinit var todoTaskRepository: TodoTaskRepository

    @BeforeEach
    fun setup() {
        jdbcTemplate = Mockito.mock(JdbcTemplate::class.java)
        todoTaskRepository = TodoTaskRepositoryImpl(jdbcTemplate)
    }

    @Test
    fun `tests findAll`() {
        // given
        val todo1 = TodoTask(UUID.randomUUID(), "Todo1", "A todo item", Status.READY)
        val todo2 = TodoTask(UUID.randomUUID(), "Todo2", "A todo item", Status.IN_PROGRESS)
        val todo3 = TodoTask(UUID.randomUUID(), "Todo3", "A todo item", Status.DONE)
        Mockito.`when`(jdbcTemplate.query(anyString(), isA<TodoTaskRowMapper>()))
            .thenReturn(listOf(todo1, todo2, todo3))

        // when
        val todoTasks = todoTaskRepository.findAll()

        // then
        assertNotNull(todoTasks)
        assertEquals(listOf(todo1, todo2, todo3), todoTasks)
    }

    @Test
    fun `tests findAll when return value is an empty list`() {
        // given
        Mockito.`when`(jdbcTemplate.query(anyString(), isA<TodoTaskRowMapper>()))
            .thenReturn(listOf(null))

        // when
        val todoTasks = todoTaskRepository.findAll()

        // then
        assertTrue(todoTasks.isEmpty())
    }

    @Test
    fun `tests findById`() {
        // given
        val uuid = UUID.randomUUID()
        val todoTask = TodoTask(uuid, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(jdbcTemplate.queryForObject(anyString(), isA<TodoTaskRowMapper>(), eq(uuid)))
            .thenReturn(todoTask)

        // when
        val actualTodoTask = todoTaskRepository.findById(uuid)

        // then
        assertEquals(todoTask, actualTodoTask)
    }

    @Test
    fun `tests findById when Todo task does not exist`() {
        // given
        val uuid = UUID.randomUUID()
        Mockito.`when`(jdbcTemplate.queryForObject(anyString(), isA<TodoTaskRowMapper>(), eq(uuid)))
            .thenThrow(EmptyResultDataAccessException(1))

        // when
        val exception = assertThrows(TodoTaskNotFoundException::class.java) {
            todoTaskRepository.findById(uuid)
        }

        // then
        assertEquals("Todo task with id=${uuid} not found", exception.message)
    }

    @Test
    fun `tests create`() {
        // given
        val uuid = UUID.randomUUID()
        val todoTask = TodoTask(uuid, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn(1)

        // when
        val actualTodoTask = todoTaskRepository.create(todoTask)

        // then
        assertEquals(todoTask, actualTodoTask)
    }

    @Test
    fun `tests update`() {
        // given
        val uuid = UUID.randomUUID()
        val todoTask = TodoTask(uuid, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyInt()))
            .thenReturn(1)

        // when
        val actualTodoTask = todoTaskRepository.update(uuid, todoTask)

        // then
        assertEquals(todoTask, actualTodoTask)
    }

    @Test
    fun `tests delete`() {
        // given
        val uuid = UUID.randomUUID()
        val todoTask = TodoTask(uuid, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(jdbcTemplate.update(anyString(), anyInt()))
            .thenReturn(1)

        // when
        val actualTodoTask = todoTaskRepository.delete(uuid, todoTask)

        // then
        assertEquals(todoTask, actualTodoTask)
    }
}