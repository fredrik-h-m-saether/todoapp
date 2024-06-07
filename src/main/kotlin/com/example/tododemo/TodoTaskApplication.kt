package com.example.tododemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoTaskApplication

fun main(args: Array<String>) {
    runApplication<TodoTaskApplication>(*args)
}
