package com.lenakotlinvuepractice.backend.repository

import com.lenakotlinvuepractice.backend.model.Word
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface WordRepository : JpaRepository<Word, Long> {
    fun findByName(@Param("name") name: String): Word

    @Transactional
    fun deleteByName(@Param("name") name: String) : Boolean

}