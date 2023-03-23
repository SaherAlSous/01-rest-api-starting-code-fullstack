package com.in28minutes.fullstack.todo

import java.time.LocalDate

open class Todo(
    open var id: Long,
    open var username: String,
    open var description: String,
    open var targetDate: LocalDate,
    open var done: Boolean
) {

    override fun toString(): String {
        return ("Todo [id=$id, username=$username" + ", description=" + description + ", targetDate="
                + targetDate) + ", done=" + done + "]"
    }
}