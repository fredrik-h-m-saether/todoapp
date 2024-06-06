package com.example.tododemo.api.mapper

import com.example.tododemo.api.dto.TodoTaskResponse
import com.example.tododemo.domain.model.TodoTask

/**
 * A mapper class for mapping between DTOs and domain models.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://www.baeldung.com/java-dto-pattern">The DTO Pattern (Data Transfer Object)</a>
 */
class TodoTaskMapper {
    companion object {

        /**
         * Maps a given [TodoTask]-object to a TodoResponse-object.
         */
        fun domainToTodoResponse(domain: TodoTask): TodoTaskResponse {
            return TodoTaskResponse(domain.id, domain.title, domain.description, domain.status)
        }
    }
}