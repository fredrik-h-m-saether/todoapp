package com.example.tododemo

import org.springframework.stereotype.Repository
import java.util.UUID


interface TodoPersistence {
    fun save (todo : Todo)
    fun getAll () : List<Todo>
    fun find (id: UUID) : Todo?
    fun updateStatus (id: UUID, finished : Boolean )
    fun delete (id: UUID)
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



@Repository
class TodoPersistenceMutableLocal: TodoPersistence {
    val local = mutableListOf<Todo>()

    fun saveToList (todo: Todo) {
        local.add(todo)
    }

    override fun save(todo: Todo) {
        // TODO : DTO should never be in persistence layer?

        saveToList(todo )
    }

    override fun getAll(): List<Todo> = local.toList()

    override fun find(id: UUID): Todo? = local.find { e -> e.id == id}

    override fun updateStatus(id: UUID, finished: Boolean) {
        this.find(id)?.let { old ->
            this.delete(old.id)
            val new = Todo(old.id, old.title, old.description, finished)
            saveToList(new)
        }
    }

    override fun delete(id: UUID) {
        local.removeIf { e -> e.id == id }
    }
}