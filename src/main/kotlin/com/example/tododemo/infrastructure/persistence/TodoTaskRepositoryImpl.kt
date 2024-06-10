package com.example.tododemo.infrastructure.persistence

import com.example.tododemo.domain.exceptions.TodoTaskNotFoundException
import com.example.tododemo.domain.exceptions.TodoTaskRepositoryException
import com.example.tododemo.domain.model.TodoTask
import com.example.tododemo.domain.repository.TodoTaskRepository
import com.example.tododemo.infrastructure.mapper.TodoTaskRowMapper
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TodoTaskRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : TodoTaskRepository {

    @Deprecated(message = "To be removed in future release.")
    override fun checkIfExists(id: UUID): Boolean {
        TODO("Must be removed in future release.")
    }

    override fun findAll(): List<TodoTask> {
        try {
            val sql = "SELECT * FROM todo_tasks"
            return jdbcTemplate.query(sql, TodoTaskRowMapper()).filterNotNull()
        } catch (ex: DataAccessException) {
            throw TodoTaskRepositoryException("An unexpected exception occurred while retrieving Todo tasks", ex)
        }
    }

    override fun findById(id: UUID): TodoTask {
        try {
            val sql = "SELECT * FROM todo_tasks WHERE ID = ?"
            return jdbcTemplate.queryForObject(sql, TodoTaskRowMapper(), id)!!
        } catch (ex: EmptyResultDataAccessException) {
            throw TodoTaskNotFoundException("Todo task with id=$id not found", ex)
        } catch (ex: DataAccessException) {
            throw TodoTaskRepositoryException("An unexpected exception occurred while querying Todo task with id=$id", ex)
        }
    }

    override fun create(todoTask: TodoTask): TodoTask {
        try {
            val sql = "INSERT INTO todo_tasks(id, title, description, status) VALUES(?, ?, ?, ?::STATUS)"
            jdbcTemplate.update(sql, todoTask.id, todoTask.title, todoTask.description, todoTask.status.toString())
            return todoTask
        } catch (ex: DataAccessException) {
            throw TodoTaskRepositoryException("An unexpected exception occurred while creating Todo task with id=${todoTask.id}", ex)
        }
    }

    override fun update(id: UUID, todoTask: TodoTask): TodoTask {
        try {
            val sql = "UPDATE todo_tasks SET title = ?, description = ?, status = ?::STATUS WHERE id = ?"
            jdbcTemplate.update(sql, todoTask.title, todoTask.description, todoTask.status.toString(), id)
            return todoTask
        } catch (ex: DataAccessException) {
            throw TodoTaskRepositoryException("An unexpected exception occurred while updating Todo task with id=${todoTask.id}", ex)
        }
    }

    override fun delete(id: UUID, todoTask: TodoTask): TodoTask {
        try {
            val sql = "DELETE FROM todo_tasks WHERE id = ?"
            jdbcTemplate.update(sql, id)
            return todoTask
        } catch (ex: DataAccessException) {
            throw TodoTaskRepositoryException("An unexpected exception occurred while updating Todo task with id=${todoTask.id}", ex)
        }
    }
}