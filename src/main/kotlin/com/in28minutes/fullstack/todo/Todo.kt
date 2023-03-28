package com.in28minutes.fullstack.todo

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
open class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 1L,
    open var username: String= "",
    open var description: String = "",
    open var targetDate: LocalDate = LocalDate.now(),
    open var done: Boolean = false
) {

    override fun toString(): String {
        return ("Todo [id=$id, username=$username" + ", description=" + description + ", targetDate="
                + targetDate) + ", done=" + done + "]"
    }
}