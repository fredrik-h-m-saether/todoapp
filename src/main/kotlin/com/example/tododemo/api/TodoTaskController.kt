package com.example.tododemo.api

import com.example.tododemo.api.dto.TodoTaskRequest
import com.example.tododemo.api.dto.TodoTaskResponse
import com.example.tododemo.domain.model.TodoTask

/**
 * An interface specifying the contract between the application and the consumer of the API.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://github.com/fredrik-h-m-saether/todoapp">Github - TodoApp</a>
 * @see <a href="https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/">
 *      DDD, Hexagonal, Onion, Clean and CQRS Architecture</a>
 */
interface TodoTaskController {

    /**
     * Retrieves a list of [TodoTask]-objects from the Application-layer and maps the elements to
     * [TodoTaskResponse]-objects before returning.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun getListOfTodoTasks(): List<TodoTaskResponse>

    /**
     * Takes a [TodoTaskRequest] as parameter and calls the service class in the Application-layer for creating a new
     * [TodoTask]-object. This object is then mapped to a [TodoTaskResponse]-object before returning.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun createNewTodoTask(todoRequest: TodoTaskRequest): TodoTaskResponse
}