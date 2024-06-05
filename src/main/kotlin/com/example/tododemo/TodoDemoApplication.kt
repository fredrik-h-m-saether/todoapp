package com.example.tododemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID


@SpringBootApplication
class TodoDemoApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoApplication>(*args)
}
