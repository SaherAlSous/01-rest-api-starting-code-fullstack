package com.in28minutes.fullstack.config

import com.in28minutes.fullstack.todo.Todo
import com.in28minutes.fullstack.todo.TodoRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class Initializer {

    @Bean
    fun dbInitializer(
        todoRepository: TodoRepository
    ): CommandLineRunner {
        return CommandLineRunner {
            val first = Todo(1, "Saher AlSous","Get AWS Certified",
                LocalDate.now().plusYears(10), false )
            val second = Todo(2, "Saher AlSous","Learn DevOps",
                LocalDate.now().plusYears(11), false )
            val third = Todo(3, "Saher AlSous","Learn Full Stack Development",
                LocalDate.now().plusYears(12), false )

            todoRepository.save(first)
            todoRepository.save(second)
            todoRepository.save(third)
        }
    }

}