package com.example.tododemo.controllers

import com.example.tododemo.models.Todo
import com.example.tododemo.service.TodoService
import com.example.tododemo.models.TodoDTO
import com.example.tododemo.repository.TodoRepository
import com.example.tododemo.repository.TodoRepository.*
import com.example.tododemo.repository.TodoRepositoryDBImpl
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TodoWebController(val service: TodoService) {
    @GetMapping("/todos")
    fun index(): ResponseEntity<List<Todo>> =
        service.findMessages().let { ResponseEntity.ok(it) }

    @PostMapping("/todos")
    fun post(@RequestBody todoDTO: TodoDTO) =
        service
            .save(todoDTO)


    @GetMapping("/todos/{id}")
    fun find(@PathVariable("id") id: UUID): ResponseEntity<Todo> =
        service
            .find(id)
            .mapToResponseEntity()


    @DeleteMapping("/todos/{id}")
    fun delete(@PathVariable("id") id: UUID): ResponseEntity<Unit> =
        try {
            service
                .delete(id)
                .ok()
        } catch (e: NotFoundException) {
            ResponseEntity.notFound().build<Unit>()
        } catch (e: NotDeletedException) {
            ResponseEntity.internalServerError().build<Unit>()
        }

    @PatchMapping("/todos/markcomplete/{id}")
    fun markComplete(@PathVariable("id") id: UUID): ResponseEntity<Unit> =
        try {
            service
                .updateStatus(id, finished = true)
                .ok()
        } catch (e: NotFoundException) {
            ResponseEntity.notFound().build()
        }


    fun Todo?.mapToResponseEntity(): ResponseEntity<Todo> {
        this ?: return ResponseEntity.notFound().build<Todo>()
        return ResponseEntity.ok(this)
    }

    fun Unit.ok() = ResponseEntity.ok().build<Unit>()
}
