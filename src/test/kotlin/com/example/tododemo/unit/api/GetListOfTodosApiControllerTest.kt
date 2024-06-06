package com.example.tododemo.unit.api

import com.example.tododemo.api.GetListOfTodosApiController
import com.example.tododemo.api.controllers.GetListOfTodosApiControllerImpl
import com.example.tododemo.api.dto.TodoResponse
import com.example.tododemo.application.GetListOfTodosApiService
import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.Todo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class GetListOfTodosApiControllerTest {

    private lateinit var getListOfTodosApiController: GetListOfTodosApiController
    private lateinit var getListOfTodosApiService: GetListOfTodosApiService

    @BeforeEach
    fun setup() {
        getListOfTodosApiService = Mockito.mock(GetListOfTodosApiService::class.java)
        getListOfTodosApiController = GetListOfTodosApiControllerImpl(getListOfTodosApiService)
    }

    @Test
    fun `given GetListOfTodosApiRepository, when calling getListOfTodosV1, assert success`() {
        // given
        val uuid = UUID.randomUUID()
        val todo1 = Todo(uuid, "Todo1", "A todo item", Status.READY)
        Mockito.`when`(getListOfTodosApiService.getListOfTodosV1())
            .thenReturn(listOf(todo1))

        // when
        val todos: List<TodoResponse> = getListOfTodosApiController.getListOfTodosV1()

        // then
        assertNotNull(todos)
        assertEquals(uuid, todos.first().id)
        assertEquals("Todo1", todos.first().title)
        assertEquals("A todo item", todos.first().description)
        assertEquals(Status.READY, todos.first().status)
    }
}