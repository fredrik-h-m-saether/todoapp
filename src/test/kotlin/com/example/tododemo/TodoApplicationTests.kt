package com.example.tododemo

import com.example.tododemo.models.Todo
import com.example.tododemo.repository.TodoJpaRepository
import com.example.tododemo.repository.TodoRepository
import com.example.tododemo.repository.TodoRepositoryDBImpl
import com.example.tododemo.service.TodoService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

//@SpringBootTest
@ExtendWith(MockKExtension::class)
class TodoApplicationTests {

    @MockK
    lateinit var todoRepository: TodoRepository

    @InjectMockKs
    lateinit var todoService: TodoService


    val someUuid: UUID = UUID.fromString("5927204e-be22-42c1-ae3c-d36675454c2d")
    val todo: Todo = mockk()

    @Test
    fun contextLoads() {
        assert(true)
    }

    @Test
    fun `When service find(id) calls is called, passes call to repo`() {
        //given
        val expected = todo
        every { todoRepository.find(someUuid) } returns expected

        //when
        val result = todoService.find(someUuid)

        //then
        verify(exactly = 1) { todoRepository.find(someUuid) }
        assertThat(result).isEqualTo(expected)
    }
}