package com.example.tododemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID


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
    fun save(todoDTO : TodoDTO) = store.save(todoDTO )
    fun find (id: String) : Todo? = store.find(id)
    fun updateStatus (id: String, finished : Boolean ) = store.updateStatus(id, finished)
    fun delete (id: String) = store.delete(id)
}

@Service
interface TodoPersistence {
    fun save (todoDTO : TodoDTO)
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

@Service
class TodoPersistenceMutableLocal: TodoPersistence {
    val local = mutableListOf<Todo>()

    fun saveToList (todo: Todo) {
        local.add(todo)
    }

    override fun save(todoDTO: TodoDTO) {
        // TODO : DTO should never be in persistence layer?
        val todo = Todo(
            id = UUID.randomUUID().toString(),
            title = todoDTO.title,
            description = todoDTO.description,
            finished = false
        )
        saveToList(todo)
    }

    override fun getAll(): List<Todo> = local.toList()

    override fun find(id: String): Todo? = local.find { e -> e.id == id}

    override fun updateStatus(id: String, finished: Boolean) {
        val old = this.find(id) ?: return

        assert(old != null)
        this.delete(id)
        val new = Todo(old.id, old.title, old.description, finished)
        saveToList(new)
    }

    override fun delete(id: String) {
        local.removeIf { e -> e.id == id }
    }

}

data class Message(val id: String?, val text: String)
data class TodoDTO(val title : String, val description: String)
data class Todo(val id: String, val title : String, val description: String, val finished: Boolean)

