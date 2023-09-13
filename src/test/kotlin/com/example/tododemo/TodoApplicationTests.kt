package com.example.tododemo

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

//@SpringBootTest
@ExtendWith(MockKExtension::class)
class TodoApplicationTests {

    @InjectMockKs
    lateinit var todoService: TodoService

    @MockK
    lateinit var todoRepository: TodoPersistence

    val someUuid: UUID = UUID.fromString("5927204e-be22-42c1-ae3c-d36675454c2d")
    val todo: Todo = mockk()

    @Test
    fun contextLoads() {
        assert(true)
    }

    @Test
    fun `Service passes find(id) calls to repo`() {
        //given
        val expected: Result<Todo> = Result.success(todo)
        every { todoRepository.find(someUuid) } returns expected

        //when
        val result = todoService.find(someUuid)

        //then
        verify(exactly = 1) { todoRepository.find(someUuid) }
        assertThat(result).isEqualTo(expected)
    }
}