package com.example.tododemo.api.errorhandling

import com.example.tododemo.domain.exceptions.TodoTaskNotFoundException
import com.example.tododemo.domain.exceptions.TodoTaskRepositoryException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Global exception handler.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 *
 * @see <a href="https://www.baeldung.com/exception-handling-for-rest-with-spring">Error Handling for REST with Spring</a>
 */
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    private val log: Logger = LoggerFactory.getLogger(RestExceptionHandler::class.java)
    private val logErrorMessage = "An error occurred for HTTP request with URI path={} - ERROR_MESSAGE={}"

    /**
     * Handles the [TodoTaskNotFoundException] by creating a new [ResponseEntity]-object with [HttpStatus.NOT_FOUND].
     */
    @ExceptionHandler(value = [TodoTaskNotFoundException::class])
    protected fun handleTodoTaskNotFoundException(ex: TodoTaskNotFoundException, request: WebRequest): ResponseEntity<Any>? {
        val httpStatus = HttpStatus.NOT_FOUND
        val uriPath = (request as ServletWebRequest).request.requestURI
        log.warn("Todo task not found", ex)
        val errorResponse = ErrorResponse(status = httpStatus.value(), message = ex.message, path = uriPath)
        return handleExceptionInternal(ex, errorResponse, HttpHeaders(), httpStatus, request)
    }

    /**
     * Handles the [TodoTaskRepositoryException] by creating a new [ResponseEntity]-object with [HttpStatus.INTERNAL_SERVER_ERROR].
     */
    @ExceptionHandler(value = [TodoTaskRepositoryException::class])
    protected fun handleTodoTaskRepositoryException(ex: TodoTaskRepositoryException, request: WebRequest): ResponseEntity<Any>? {
        val httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        val uriPath = (request as ServletWebRequest).request.requestURI
        log.warn("An unexpected Repository-exception occurred", ex)
        val errorResponse = ErrorResponse(status = httpStatus.value(), message = ex.message, path = uriPath)
        return handleExceptionInternal(ex, errorResponse, HttpHeaders(), httpStatus, request)
    }
}