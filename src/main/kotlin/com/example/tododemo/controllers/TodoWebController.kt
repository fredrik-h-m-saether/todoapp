package com.example.tododemo.controllers

import com.example.tododemo.service.TodoService
import com.example.tododemo.models.Todo
import com.example.tododemo.models.TodoDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

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

    @PatchMapping("/todos/markcomplete/{id}")
    fun markComplete(@PathVariable("id") id: UUID) =
        service
            .updateStatus(id, finished = true)

    fun todoNotFoundStatusResponse(uuid: UUID) = ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Todo not found, maybe you got the wrong id? (received id=$uuid)"
    )

}