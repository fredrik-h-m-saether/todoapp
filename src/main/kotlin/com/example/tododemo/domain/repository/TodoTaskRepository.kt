package com.example.tododemo.domain.repository

import com.example.tododemo.domain.exceptions.TodoTaskNotFoundException
import com.example.tododemo.domain.model.TodoTask
import java.util.UUID

/**
 * The Repository class acts as an abstraction between the Application-layer and the Infrastructure-layer, so that
 * these layers are decoupled from each other.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://github.com/fredrik-h-m-saether/todoapp">Github - TodoApp</a>
 * @see <a href="https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/">
 *      DDD, Hexagonal, Onion, Clean and CQRS Architecture</a>
 * @see <a href="https://www.baeldung.com/hexagonal-architecture-ddd-spring">
 *      Organizing Layers Using Hexagonal Architecture, DDD and Spring</a>
 */
interface TodoTaskRepository {

    /**
     * Checks if the [TodoTask]-object associated by [id] exists.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    @Deprecated(message = "To be removed in a future release.")
    fun checkIfExists(id: UUID): Boolean

    /**
     * Retrieves a list of [TodoTask]-objects.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun findAll(): List<TodoTask>

    /**
     * Retrieves a single [TodoTask]-object associated by [id].
     *
     * @throws TodoTaskNotFoundException if the [TodoTask]-object associated by [id] is not found.
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun findById(id: UUID): TodoTask?

    /**
     * Creates a new [TodoTask].
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun create(todoTask: TodoTask): TodoTask

    /**
     * Updates an existing [TodoTask] associated by [id].
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun update(id: UUID, todoTask: TodoTask): TodoTask

    /**
     * Deletes an existing [TodoTask] associated by [id].
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun delete(id: UUID, todoTask: TodoTask): TodoTask
}