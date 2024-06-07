package com.example.tododemo.api

import com.example.tododemo.api.dto.TodoTaskRequest
import com.example.tododemo.api.dto.TodoTaskResponse
import com.example.tododemo.domain.model.TodoTask
import java.util.*

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
     * Retrieves a list of [TodoTaskResponse]-objects.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun getAllTodoTasks(): List<TodoTaskResponse>

    /**
     * Takes an [id] as parameter and deletes an existing [TodoTask]-object associated by the id.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun getTodoTask(id: UUID): TodoTaskResponse

    /**
     * Takes a [TodoTaskRequest] as parameter and creates a new [TodoTask]-object.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun createNewTodoTask(todoRequest: TodoTaskRequest): TodoTaskResponse

    /**
     * Takes an [id] and a [todoTaskRequest] as parameter and updates an existing [TodoTask]-object associated by the id.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun updateExistingTodoTask(id: UUID, todoTaskRequest: TodoTaskRequest): TodoTaskResponse

    /**
     * Takes an [id] as parameter and deletes an existing [TodoTask]-object associated by the id.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun deleteExistingTodoTask(id: UUID): TodoTaskResponse
}