package com.lenakotlinvuepractice.backend.repository

import com.lenakotlinvuepractice.backend.model.Example
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface ExampleRepository : JpaRepository<Example, Long> {
    fun findByText(@Param("text") text: String): Example

    @Transactional
    fun deleteByText(@Param("text") text: String) : Boolean
}