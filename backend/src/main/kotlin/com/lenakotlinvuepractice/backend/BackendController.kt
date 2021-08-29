package com.lenakotlinvuepractice.backend

import com.lenakotlinvuepractice.backend.model.Greeting
import com.lenakotlinvuepractice.backend.model.User
import com.lenakotlinvuepractice.backend.repository.PersonRepository
import com.lenakotlinvuepractice.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong
import org.springframework.security.core.Authentication

@RestController
@RequestMapping("/api")
class BackendController() {
    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var userRepository: UserRepository

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(), "Hello, $name")

    @GetMapping("/persons")
    fun getPersons() = personRepository.findAll()


    @GetMapping("/usercontent")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseBody
    fun getUserContent(authentication: Authentication): String {
        val user: User = userRepository.findByUsername(authentication.name).get()
        return "Hello " + user.firstName + " " + user.lastName + "!"
    }


    @GetMapping("/admincontent")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    fun getAdminContent(): String {
        return "Admin's content"
    }

}