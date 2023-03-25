package com.in28minutes.fullstack.todo

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TodoService {

    private val todos = mutableListOf(
        Todo(1, "Saher AlSous","Get AWS Certified",
            LocalDate.now().plusYears(10), false ),
        Todo(2, "Saher AlSous","Learn DevOps",
            LocalDate.now().plusYears(11), false ),
        Todo(3, "Saher AlSous","Learn Full Stack Development",
            LocalDate.now().plusYears(12), false )
    )

    fun findByUsername(username: String): List<Todo> {
        return todos.filter { it.username == username }
    }

    fun addTodo(
        username: String,
        description: String,
        targetDate: LocalDate,
        done: Boolean
    ): Todo {
        val myTodo = Todo(
            id = todos.last().id + 1L,
            username = username,
            description = description,
            targetDate = targetDate,
            done = done)
        todos.add(myTodo)
        return myTodo
    }

    fun deleteById(id: Long) {
        todos.removeIf { it.id == id }
    }

    fun findById(id: Long): Todo {
        return todos.find { it.id == id } ?: throw RuntimeException("Not Found")
    }

    fun updateTodo(todo: Todo) {
        deleteById(todo.id)
        todos.add(todo)
    }

}