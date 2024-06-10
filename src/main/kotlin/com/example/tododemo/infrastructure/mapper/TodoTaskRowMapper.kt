package com.example.tododemo.infrastructure.mapper

import com.example.tododemo.domain.enums.Status
import com.example.tododemo.domain.model.TodoTask
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.UUID

class TodoTaskRowMapper : RowMapper<TodoTask?> {

    override fun mapRow(rs: ResultSet, rowNum: Int): TodoTask {
        return TodoTask(
            UUID.fromString(rs.getString("id")),
            rs.getString("title"),
            rs.getString("description"),
            Status.valueOf(rs.getString("status"))
        )
    }
}