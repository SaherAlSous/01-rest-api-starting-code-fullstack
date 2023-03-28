package com.in28minutes.fullstack.todo

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByUsername(username: String): List<Todo>

    fun existsByUsername(username: String): Boolean
}