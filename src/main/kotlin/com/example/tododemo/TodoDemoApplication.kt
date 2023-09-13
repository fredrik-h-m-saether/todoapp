package com.example.tododemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@SpringBootApplication
class TodoDemoApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoApplication>(*args)
}

@RestController
class TodoWebController(val service: TodoService) {
    @GetMapping("/todos")
    fun index(): List<Todo> = service.findMessages()

    @PostMapping("/todos")
    fun post(@RequestBody todoDTO: TodoDTO) =
        service
            .save(todoDTO)


    @GetMapping("/todos/{id}")
    fun find(@PathVariable("id") id: UUID) =
        service
            .find(id)
            .fold(
                onSuccess = { it },
                onFailure = { todoNotFoundStatusResponse(id) }
            )

    @DeleteMapping("/todos/{id}")
    fun delete(@PathVariable("id") id: UUID) =
        service
            .delete(id)
            .fold(
                onSuccess = { },
                onFailure = { todoNotFoundStatusResponse(id) }
            )

    @PatchMapping("/todos/markcomplete/{id}")
    fun markComplete(@PathVariable("id") id: UUID) =
        service
            .updateStatus(id, finished = true)
            .fold(
                onSuccess = { },
                onFailure = { todoNotFoundStatusResponse(id) }
            )

    fun todoNotFoundStatusResponse(uuid: UUID) = ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Todo not found, maybe you got the wrong id? (received id=$uuid)"
    )

}


data class TodoDTO(val title: String, val description: String)

