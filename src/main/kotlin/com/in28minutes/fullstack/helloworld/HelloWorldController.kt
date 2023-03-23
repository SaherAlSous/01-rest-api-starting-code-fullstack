package com.in28minutes.fullstack.helloworld

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    name = "Hello World Controller",
    value = ["/"]
)
class HelloWorldController {

    @GetMapping(
        name = "Getting Hello World String",
        value = ["hello-world"])
    fun helloWorld(): String {
        return "Hello JS/React World"
    }

    @GetMapping(
        name = "Getting Hello World Bean",
        value = ["hello-world-bean"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello JS/React World")
    }

    @GetMapping(
        name = "Getting Hello World with Path variable",
        value = ["hello-world/path-variable/{name}"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloWorldPathVariable(
        @PathVariable("name") name: String
    ): HelloWorldBean {
        return HelloWorldBean("Hello JS/React World, $name")
    }
}