package com.example.tododemo.unit.domain.repository

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.Todo
import com.example.tododemo.domain.repository.GetListOfTodosApiRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class GetListOfTodosRepositoryTest {

    private lateinit var getListOfTodosApiRepository: GetListOfTodosApiRepository

    @BeforeEach
    fun setup() {
        getListOfTodosApiRepository = Mockito.mock(GetListOfTodosApiRepository::class.java)
    }

    @Test
    fun `given GetListOfTodosApiRepository, when calling findAllTodos, assert success`() {
        // given
        val todo1 = Todo(UUID.randomUUID(), "Todo1", "A todo item", Status.READY)
        val todo2 = Todo(UUID.randomUUID(), "Todo2", "A todo item", Status.IN_PROGRESS)
        val todo3 = Todo(UUID.randomUUID(), "Todo3", "A todo item", Status.DONE)
        Mockito.`when`(getListOfTodosApiRepository.findAllTodosV1())
            .thenReturn(listOf(todo1, todo2, todo3))

        // when
        val todos: List<Todo> = getListOfTodosApiRepository.findAllTodosV1()

        // then
        assertNotNull(todos)
        assertEquals(listOf(todo1, todo2, todo3), todos)
    }
}