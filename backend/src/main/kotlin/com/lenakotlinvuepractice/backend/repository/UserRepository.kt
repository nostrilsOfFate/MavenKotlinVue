package com.lenakotlinvuepractice.backend.repository

import com.lenakotlinvuepractice.backend.model.Role
import com.lenakotlinvuepractice.backend.model.User
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.*
import javax.transaction.Transactional

interface UserRepository: JpaRepository<User, Long> {

    fun existsByUsername(@Param("username") username: String): Boolean

    fun findByUsername(@Param("username") username: String): Optional<User>

    fun findByEmail(@Param("email") email: String): Optional<SecurityProperties.User>

    @Transactional
    fun deleteByUsername(@Param("username") username: String)

}

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(@Param("name") name: String): Role
}