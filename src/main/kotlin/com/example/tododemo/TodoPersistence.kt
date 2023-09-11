package com.example.tododemo

import org.springframework.stereotype.Repository
import java.util.*


interface TodoPersistence {
    fun save(todo: Todo)
    fun getAll(): List<Todo>
    fun find(id: UUID): Result<Todo>
    fun updateStatus(id: UUID, finished: Boolean): Result<Unit>
    fun delete(id: UUID): Result<Unit>
}

/*
class TodoPersistenceDB (val db: JdbcTemplate): TodoPersistence {
    override fun save(todo: TodoDTO) {
        val id = todo.id ?: UUID.randomUUID().toString()
        val done = false;
        db.update("insert into todos_v01 values ( ?, ?)",
            id, todos_v01.text)
    }

    override fun getAll(): List<Todo> = db.query("select * from message") { response, _ ->
        Todo(response.getString("id"), response.getString("text"))
    }

    override fun get(id: String): Todo {
        TODO("Not yet implemented")
    }

    override fun updateStatus(id: String, finished: Boolean) {
        TODO("Not yet implemented")
    }
}
*/

fun Todo?.toResult(id: UUID): Result<Todo> {
    this ?: return Result.failure(TodoNotFoundException(id))
    return Result.success(this)
}

@Repository
class TodoPersistenceMutableLocal : TodoPersistence {
    val local = mutableListOf<Todo>()

    fun saveToList(todo: Todo) {
        local.add(todo)
    }

    override fun save(todo: Todo) {
        saveToList(todo)
    }

    override fun getAll(): List<Todo> = local.toList()

    override fun find(id: UUID): Result<Todo> = local.find { e -> e.id == id }.toResult(id)

    override fun updateStatus(id: UUID, finished: Boolean): Result<Unit> {
        return this
            .find(id)
            .map { old ->
                this.delete(old.id)
                val new = Todo(old.id, old.title, old.description, finished)
                saveToList(new)
            }
    }

    override fun delete(id: UUID): Result<Unit> {
        local
            .removeIf { e -> e.id == id }
            .let { found ->
                if (found)
                    return Result.success(Unit)
                else
                    return Result.failure(TodoNotFoundException(id))
            }
    }
}