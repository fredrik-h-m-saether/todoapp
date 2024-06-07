package com.example.tododemo.api.dto

import com.example.tododemo.domain.enums.Status
import java.util.UUID

/**
 * The [TodoTaskResponse] class is a Data Transfer Object (DTO) for serializing the domain-model to JSON.
 *
 * @property id En unik ID for [TodoTaskResponse].
 * @property title En tittel for [TodoTaskResponse].
 * @property title En beskrivelse av [TodoTaskResponse].
 * @property status En [Status] for [TodoTaskResponse].
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://github.com/fredrik-h-m-saether/todoapp">Github - TodoApp</a>
 * @see <a href="https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/">
 *      DDD, Hexagonal, Onion, Clean and CQRS Architecture</a>
 * @see <a href="https://www.baeldung.com/hexagonal-architecture-ddd-spring">
 *      Organizing Layers Using Hexagonal Architecture, DDD and Spring</a>
 */
data class TodoTaskResponse(val id: UUID, val title: String?, val description: String?, val status: Status)