package com.example.tododemo.domain.model

import com.example.tododemo.domain.enums.Status
import java.util.UUID

/**
 * The [Todo] class is the domain model of our application.
 *
 * @property id En unik ID for [Todo].
 * @property title En tittel for [Todo].
 * @property title En beskrivelse av [Todo].
 * @property status En [Status] for [Todo].
 *
 * @author Øystein Opedal
 * @since 0.0.1
 * @see <a href="https://github.com/fredrik-h-m-saether/todoapp">Github - TodoApp</a>
 * @see <a href="https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/">
 *      DDD, Hexagonal, Onion, Clean and CQRS Architecture</a>
 */
data class Todo(val id: UUID, val title: String, val description: String, val status: Status)