package com.in28minutes.fullstack.todo

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {


    fun findAll(): List<Todo> {
        return todoRepository.findAll()
    }

    fun findByUsername(username: String): List<Todo> {
        return if (todoRepository.existsByUsername(username))
            todoRepository.findByUsername(username)
            else throw RuntimeException("No todos found")
    }

    fun addTodo(
        username: String,
        description: String,
        targetDate: LocalDate,
        done: Boolean
    ): Todo {
        val myTodo = Todo(
            username = username,
            description = description,
            targetDate = targetDate,
            done = done)
        return todoRepository.save(myTodo)
    }

    fun deleteById(id: Long) {
        todoRepository.deleteById(id)
    }

    fun findById(id: Long): Todo {
        val todo =  todoRepository.findById(id)
        return if (todo.isPresent) todo.get()
         else throw RuntimeException("Not Found")
    }

    fun updateTodo(todo: Todo) {
        deleteById(todo.id)
        todoRepository.save(todo)
    }

}