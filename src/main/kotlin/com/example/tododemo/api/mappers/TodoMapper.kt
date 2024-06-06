package com.example.tododemo.api.mappers

import com.example.tododemo.api.dto.TodoResponse
import com.example.tododemo.domain.model.Todo

/**
 * A mapper class for mapping between DTOs and domain models.
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://www.baeldung.com/java-dto-pattern">The DTO Pattern (Data Transfer Object)</a>
 */
class TodoMapper {
    companion object {

        /**
         * Maps a given Todo-object to a TodoResponse-object.
         *
         */
        fun domainToTodoResponse(domain: Todo): TodoResponse {
            return TodoResponse(domain.id, domain.title, domain.description, domain.status)
        }
    }
}