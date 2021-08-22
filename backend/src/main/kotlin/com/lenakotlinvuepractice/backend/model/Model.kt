package com.lenakotlinvuepractice.backend.model

import javax.persistence.*

data class Greeting(val id: Long, val content: String)


@Entity
@Table(name = "person")
data class Person(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(nullable = false)
        val name: String
)