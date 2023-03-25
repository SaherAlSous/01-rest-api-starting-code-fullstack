package com.in28minutes.fullstack.todo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    name = "Todos Controller",
    value = ["/users"]
)
class TodoResources(
    private val todoService: TodoService
) {

    @GetMapping(
        name = "Getting todos",
        value = ["/{username}/todos"])
    fun getTodos(
        @PathVariable("username") username: String
    ) : List<Todo> {
        return todoService.findByUsername(username)
    }

    @GetMapping(
        name = "Get a single Todo",
        value = ["/{username}/todos/{id}"])
    fun getTodo(
        @PathVariable("username") username: String,
        @PathVariable("id") id: Long
    ) : Todo {
        return todoService.findById(id)
    }

    @DeleteMapping(
        name = "Delete Todo",
        value = ["/{username}/todos/{id}"])
    fun deleteTodo(
        @PathVariable("username") username: String,
        @PathVariable("id") id: Long
    ): ResponseEntity<String> {
        todoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping(
        name = "update todo",
        value = ["/{username}/todos/{id}"])
    fun updateTodo(
        @PathVariable("username") username: String,
        @PathVariable("id") id: Long,
        @RequestBody todo: Todo
    ): Todo {
        todoService.updateTodo(todo)
        return todo
    }

    @PostMapping(
        name = "Create new todo",
        value = ["/{username}/todos"])
    fun createTodo(
        @PathVariable("username") username: String,
        @RequestBody todo: Todo
    ): Todo {
        return todoService.addTodo(
            username = username,
            description = todo.description,
            targetDate = todo.targetDate,
            done = todo.done
        )
    }

}