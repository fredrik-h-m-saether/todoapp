package com.example.tododemo.unit.domain

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.Todo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class TodoTest {

    @Test
    fun `given a Todo object, when accessing fields, assert success`() {
        // given
        val uuid = UUID.randomUUID()
        val todo = Todo(uuid, "Todo1", "A todo item", Status.READY)

        // when && then
        assertEquals(uuid, todo.id)
        assertEquals("Todo1", todo.title)
        assertEquals("A todo item", todo.description)
        assertEquals(Status.READY, todo.status)
    }
}