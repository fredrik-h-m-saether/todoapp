package com.example.tododemo.application

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import java.util.UUID

/**
 * The Service class on the application layer orchestrates the use cases.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://github.com/fredrik-h-m-saether/todoapp">Github - TodoApp</a>
 * @see <a href="https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/">
 *      DDD, Hexagonal, Onion, Clean and CQRS Architecture</a>
 * @see <a href="https://www.baeldung.com/hexagonal-architecture-ddd-spring">
 *      Organizing Layers Using Hexagonal Architecture, DDD and Spring</a>
 */
interface TodoTaskService {

    /**
     * Retrieves a list of [TodoTask]-objects.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun getListOfTodoTasks(): List<TodoTask>

    /**
     * Retrieves an existing [TodoTask]-object associated by [id].
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun getTodoTask(id: UUID): TodoTask


    /**
     * Creates a new [TodoTask]-object with [title] and [description] as values.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun createNewTodoTask(title: String?, description: String?): TodoTask

    /**
     * Updates an existing [TodoTask]-object associated by [id] with [title] (opt.), [description] (opt.) and [status] (opt.) as values.
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun updateExistingTodoTask(id: UUID, title: String?, description: String?, status: Status?): TodoTask

    /**
     * Deletes an existing [TodoTask]-object associated by [id].
     *
     * @see <a href="https://journit.atlassian.net/wiki/spaces/~63d512d64a3c3294ac05bb66/pages/7667713/TodoApp+-+Get+list+of+TODOs">
     *      User story - Get list of TODOs</a>
     */
    fun deleteExistingTodoTask(id: UUID): TodoTask
}