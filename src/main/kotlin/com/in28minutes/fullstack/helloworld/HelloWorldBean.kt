package com.in28minutes.fullstack.helloworld

data class HelloWorldBean(
    var message: String = ""
) {
    override fun toString(): String {
        return "HelloWorldBean [message= $message]"
    }
}
