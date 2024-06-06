package com.example.tododemo.api.controllers;

import com.example.tododemo.api.GetListOfTodosApiController
import com.example.tododemo.api.dto.TodoResponse
import com.example.tododemo.api.mappers.TodoMapper
import com.example.tododemo.application.GetListOfTodosApiService
import com.example.tododemo.domain.model.Todo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * A Controller class providing REST APIs for different clients.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://www.baeldung.com/rest-with-spring-series">REST with Spring Tutorial</a>
 */
//@RestController
//@RequestMapping("/api")
class GetListOfTodosApiControllerImpl(private val getListOfTodosApiService: GetListOfTodosApiService)
    : GetListOfTodosApiController {

    private val LOGGER: Logger = LoggerFactory.getLogger(GetListOfTodosApiController::class.java)

    //@GetMapping(value = ["/v1/todos"])
    override fun getListOfTodosV1(): List<TodoResponse> {
        LOGGER.debug("HTTP GET /api/v1/todos")
        return getListOfTodosApiService.getListOfTodosV1()
            .map { TodoMapper.domainToTodoResponse(it) }
            .toList()
    }
}
