package com.example.tododemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*


@SpringBootApplication
class TodoDemoApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoApplication>(*args)
}

@RestController
class MessageController(val service: TodoService) {
    @GetMapping("/")
    fun index(): List<Todo> = service.findMessages()

    @PostMapping("/new")
    fun post(@RequestBody todoDTO: TodoDTO) : String {
        service.save(todoDTO)
        return "Save for ${todoDTO.title} sent"
    }

    @GetMapping("/find")
    fun find(@RequestParam("id") id: String) = service.find(id) ?: "Item not found (id: $id)"

    @PostMapping("/delete")
    fun delete(@RequestParam("id") id: String) : String {
        service.delete(id)
        return "Delete for $id sent"
    }

    @PostMapping("/markfinished")
    fun markFinished(@RequestParam("id") id: String) {
        service.updateStatus(id, finished = true)
    }
    //fun index(@RequestParam("name") name: String) = "Hello, $name!"
}




data class TodoDTO(val title : String, val description: String)

