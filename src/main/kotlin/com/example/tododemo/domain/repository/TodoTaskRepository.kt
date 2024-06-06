package com.example.tododemo.domain.repository

import com.example.tododemo.domain.model.TodoTask

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
     * Retrieves a list of [TodoTask]-objects from the Infrastructure layer.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun findAll(): List<TodoTask>

    /**
     * Creates a new [TodoTask] in the Infrastructure layer.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun create(todoTask: TodoTask): TodoTask
}