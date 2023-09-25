package com.example.tododemo

import com.example.tododemo.models.Todo
import com.example.tododemo.models.TodoDTO
import com.example.tododemo.repository.TodoRepository
import com.example.tododemo.service.TodoService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*

@ExtendWith(MockKExtension::class)
class TodoDemoApplicationTests {

    @InjectMockKs
    lateinit var todoService: TodoService

    @MockK
    lateinit var todoRepository: TodoRepository

    private val someUuid: UUID = UUID.fromString("5927204e-be22-42c1-ae3c-d36675454c2d")
    private val aTodo: Todo = mockk()

    @Test
    fun contextLoads() {
        assert(true)
    }

    @Test
    fun todoServiceGivesEmptyList() {
        every { todoRepository.getAll() } returns listOf()
        assert(todoService.findMessages().isEmpty())
    }

    @Test
    fun todoServiceAddingOneGivesOne() {
        val aTodoDTO: TodoDTO = mockk()
        val expected = aTodo
        every { aTodoDTO.toTodo() } returns aTodo
        every { todoRepository.save(any()) } returns expected

        val result = todoService.save(aTodoDTO)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `Given UUID, queries  service with it`() {
        every { todoRepository.find(someUuid) } returns aTodo

        val result = todoService.find(someUuid)

        verify(exactly = 1) { todoRepository.find(someUuid) }
        assertThat(result).isEqualTo(aTodo)
    }

    @Test
    fun `Service is given UUID not in persistence, Returns returns error`() {
        every { todoRepository.find(someUuid) } throws NotFoundException()

        assertThrows<NotFoundException> {todoService.find(someUuid)}
    }

//todo: A repository test, not service

//    @Test
//    fun `Service Adding One Then Deleting One Gives Empty`() {
//        todoService.save(TodoDTO("SomeTitle", "Description"))
//        val firstID = todoService.findMessages()[0].id
//        todoService.delete(firstID)
//        assert(todoService.findMessages().isEmpty())
//    }


}
