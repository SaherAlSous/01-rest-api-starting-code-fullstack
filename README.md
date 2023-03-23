# 01-rest-api-starting-code-fullstack for in28minutes spring boot / framework course
Kotlin
This is the kotlin version of the in28minutes code for the full stack spring boor application.

## /build.gradle.kts

```
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.in28minutes"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
```

## /com.in28minutes.fullstack.helloworld
```
data class HelloWorldBean(
    var message: String = ""
) {
    override fun toString(): String {
        return "HelloWorldBean [message= $message]"
    }
}
```

## /com.in28minutes.fullstack.helloworld
```

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
        value = ["hello-world"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloWorld(): String {
        return "Hello World"
    }

    @GetMapping(
        name = "Getting Hello World Bean",
        value = ["hello-world-bean"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello World")
    }

    @GetMapping(
        name = "Getting Hello World with Path variable",
        value = ["/hello-world/path-variable/{name}"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloWorldPathVariable(
        @PathVariable("name") name: String
    ): HelloWorldBean {
        return HelloWorldBean("Hello World, $name")
    }
}
```

## /com.in28minutes.fullstack.todo
```
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
```

## /com.in28minutes.fullstack.todo
```

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TodoService {

    private val todos = mutableListOf<Todo>(
        Todo(1, "in28minutes","Get AWS Certified",
            LocalDate.now().plusYears(10), false ),
        Todo(2, "in28minutes","Learn DevOps",
            LocalDate.now().plusYears(11), false ),
        Todo(3, "in28minutes","Learn Full Stack Development",
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
            id = todos.size + 1L,
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
```

## /com.in28minutes.fullstack
```
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FullstackApplication

fun main(args: Array<String>) {
	runApplication<FullstackApplication>(*args)
}
```
