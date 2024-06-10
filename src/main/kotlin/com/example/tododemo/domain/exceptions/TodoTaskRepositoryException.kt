package com.example.tododemo.domain.exceptions

import com.example.tododemo.domain.model.TodoTask

/**
 * Indicates that a [TodoTask] does not exist.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 *
 * @see <a href="https://www.baeldung.com/kotlin/exception-handling">Kotlin Exception Handling</a>
 */
class TodoTaskRepositoryException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}