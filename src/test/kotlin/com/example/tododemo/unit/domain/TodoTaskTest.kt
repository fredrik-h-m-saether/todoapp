package com.example.tododemo.unit.domain

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class TodoTaskTest {

    @Test
    fun `given a TodoTask object, when accessing fields, assert success`() {
        // given
        val uuid = UUID.randomUUID()
        val todo = TodoTask(uuid, "Todo1", "A todo item", Status.CREATED)

        // when && then
        assertEquals(uuid, todo.id)
        assertEquals("Todo1", todo.title)
        assertEquals("A todo item", todo.description)
        assertEquals(Status.CREATED, todo.status)
    }
}