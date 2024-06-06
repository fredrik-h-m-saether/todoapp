package com.example.tododemo.unit.application

import com.example.tododemo.application.GetListOfTodosApiService
import com.example.tododemo.application.services.GetListOfTodosApiServiceImpl
import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.Todo
import com.example.tododemo.domain.repository.GetListOfTodosApiRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*
import kotlin.collections.List

class GetListOfTodosApiServiceTest {

    private lateinit var getListOfTodosApiRepository: GetListOfTodosApiRepository
    private lateinit var getListOfTodosApiService: GetListOfTodosApiService

    @BeforeEach
    fun setup() {
        getListOfTodosApiRepository = Mockito.mock(GetListOfTodosApiRepository::class.java)
        getListOfTodosApiService = GetListOfTodosApiServiceImpl(getListOfTodosApiRepository)
    }

    @Test
    fun `given GetListOfTodosApiRepository, when calling getListOfTodosV1, assert success`() {
        // given
        val todo1 = Todo(UUID.randomUUID(), "Todo1", "A todo item", Status.READY)
        val todo2 = Todo(UUID.randomUUID(), "Todo2", "A todo item", Status.IN_PROGRESS)
        val todo3 = Todo(UUID.randomUUID(), "Todo3", "A todo item", Status.DONE)
        Mockito.`when`(getListOfTodosApiRepository.findAllTodosV1())
            .thenReturn(listOf(todo1, todo2, todo3))

        // when
        val todos: List<Todo> = getListOfTodosApiService.getListOfTodosV1()

        // then
        assertNotNull(todos)
        assertEquals(listOf(todo1, todo2, todo3), todos)
    }
}