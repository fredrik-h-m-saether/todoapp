package com.example.tododemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*


@SpringBootApplication
class TodoDemoApplication

fun main(args: Array<String>) {
    runApplication<TodoDemoApplication>(*args)
}

@RestController
class MessageController(val service: TodoService) {
    @GetMapping("/")
    fun index(): List<Todo> = service.findMessages()

    @PostMapping("/new")
    fun post(@RequestBody todoDTO: TodoDTO) : String {
        service.save(todoDTO)
        return "Save for ${todoDTO.title} sent"
    }

    @GetMapping("/find")
    fun find(@RequestParam("id") id: String) = service.find(id) ?: "Item not found (id: $id)"

    @PostMapping("/delete")
    fun delete(@RequestParam("id") id: String) : String {
        service.delete(id)
        return "Delete for $id sent"
    }

    @PostMapping("/markfinished")
    fun markFinished(@RequestParam("id") id: String) {
        service.updateStatus(id, finished = true)
    }
    //fun index(@RequestParam("name") name: String) = "Hello, $name!"
}


@Service
class TodoService (val store: TodoPersistence) {
    fun findMessages(): List<Todo> = store.getAll()
    fun save(todoDTO : TodoDTO) = store.save(todoDTO.toTodo() )
    fun find (id: String) : Todo? = store.find(id)
    fun updateStatus (id: String, finished : Boolean ) = store.updateStatus(id, finished)
    fun delete (id: String) = store.delete(id)
}


interface TodoPersistence {
    fun save (todo : Todo)
    fun getAll () : List<Todo>
    fun find (id: String) : Todo?
    fun updateStatus (id: String, finished : Boolean )
    fun delete (id: String)
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

fun TodoDTO.toTodo() =Todo(
    id = UUID.randomUUID().toString(),
    title = title,
    description = description,
    finished = false
)

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

    override fun find(id: String): Todo? = local.find { e -> e.id == id}

    override fun updateStatus(id: String, finished: Boolean) {
        this.find(id)?.let { old ->
            this.delete(old.id)
            val new = Todo(old.id, old.title, old.description, finished)
            saveToList(new)
        }
    }

    override fun delete(id: String) {
        local.removeIf { e -> e.id == id }
    }
}

data class TodoDTO(val title : String, val description: String)
data class Todo(val id: String, val title : String, val description: String, val finished: Boolean)

