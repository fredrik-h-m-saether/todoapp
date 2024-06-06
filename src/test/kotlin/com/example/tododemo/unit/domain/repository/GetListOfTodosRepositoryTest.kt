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
    fun `given a Todo object, when accessing fields, assert success`() {
        // given
        val uuid = UUID.randomUUID()
        val todo1 = Todo(uuid, "Todo1", "A todo item", Status.READY)
        val todo2 = Todo(uuid, "Todo2", "A todo item", Status.IN_PROGRESS)
        val todo3 = Todo(uuid, "Todo3", "A todo item", Status.DONE)
        Mockito.`when`(getListOfTodosApiRepository.findAllTodos())
            .thenReturn(listOf(todo1, todo2, todo3))

        // when
        val todos: List<Todo> = getListOfTodosApiRepository.findAllTodos()

        // then
        assertNotNull(todos)
        assertEquals(listOf(todo1, todo2, todo3), todos)
    }
}