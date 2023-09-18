package com.example.tododemo

import com.example.tododemo.repository.TodoPersistenceMutableLocal
import com.example.tododemo.service.TodoService
import org.junit.jupiter.api.Test
import java.util.*


class TodoDemoApplicationTests {

    private fun mockedService() = TodoService(TodoPersistenceMutableLocal())

    @Test
    fun contextLoads() {
        assert(true)
    }

    @Test
    fun todoServiceGivesEmptyList() {
        val service = mockedService()
        assert(service.findMessages().isEmpty())
    }

    @Test
    fun todoServiceAddingOneGivesOne() {
        val service = mockedService()
        service.save(TodoDTO("SomeTitle", "Description"))
        assert(service.findMessages().isNotEmpty())
    }

    @Test
    fun todoServiceAddingOneThenFindingIT() {
        val service = mockedService()
        service.save(TodoDTO("SomeTitle", "Description"))
        val firstID = service.findMessages()[0].id
        assert(service.find(firstID).isSuccess)
    }

    @Test
    fun todoServiceFindingRandomGivesFailure() {
        val service = mockedService()
        val random = UUID.randomUUID()
        assert(service.find(random).isFailure)
    }

    @Test
    fun todoServiceFindingTodoNotInPersistenceFails() {
        val service = mockedService()
        service.save(TodoDTO("SomeTitle", "Description"))
        val firstID = service.findMessages()[0].id
        var random = firstID
        while (firstID == random)
            random = UUID.randomUUID()

        assert(service.find(random).isFailure)
    }

    @Test
    fun todoServiceAddingOneThenDeletingOneGivesEmpty() {
        val service = mockedService()
        service.save(TodoDTO("SomeTitle", "Description"))
        val firstID = service.findMessages()[0].id
        service.delete(firstID)
        assert(service.findMessages().isEmpty())
    }


}
