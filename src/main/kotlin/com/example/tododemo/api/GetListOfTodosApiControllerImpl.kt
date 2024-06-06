package com.example.tododemo.api;

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
@RestController
@RequestMapping("/api")
class GetListOfTodosApiControllerImpl : GetListOfTodosApiController {

    private val LOGGER: Logger = LoggerFactory.getLogger(GetListOfTodosApiController::class.java)

    @GetMapping(value = ["/v1/todos"])
    override fun getListOfTodosV1(): List<String> {
        LOGGER.info("Get all Todos")
        return listOf("todo1", "todo2", "todo3")
    }
}
