package com.lenakotlinvuepractice.backend.repository

import com.lenakotlinvuepractice.backend.model.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: CrudRepository<Person, Long> {}