package com.example.tododemo.domain.model

import com.example.tododemo.domain.enums.Status
import java.util.UUID

/**
 * The [TodoTask] class is the domain model of our application.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://github.com/fredrik-h-m-saether/todoapp">Github - TodoApp</a>
 * @see <a href="https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/">
 *      DDD, Hexagonal, Onion, Clean and CQRS Architecture</a>
 * @see <a href="https://www.baeldung.com/hexagonal-architecture-ddd-spring">
 *      Organizing Layers Using Hexagonal Architecture, DDD and Spring</a>
 */
data class TodoTask(val id: UUID, val title: String?, val description: String?, val status: Status) {

    fun updateTodoTask(title: String?, description: String?, status: Status?): TodoTask {
        return this.copy(
            title = title ?: this.title,
            description = description ?: this.description,
            status = status ?: this.status
        )
    }
}