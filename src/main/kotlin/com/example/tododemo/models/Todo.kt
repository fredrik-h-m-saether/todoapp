package com.example.tododemo.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.context.annotation.Primary
import java.util.*


@Entity
data class Todo(
    @Id
    val id: UUID,
    val title: String,
    val description: String,
    val finished: Boolean
)