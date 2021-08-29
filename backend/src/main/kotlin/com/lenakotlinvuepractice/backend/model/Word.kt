package com.lenakotlinvuepractice.backend.model

import javax.persistence.*

@Entity
@Table(name = "word")
class Word (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(name="name")
    var name: String,

    @Column(name="translation")
    var translation: String

) {
    //constructor() : this() {}

    constructor( name: String, translation: String): this(1, name, translation) {
        this.name = name
        this.translation = translation
    }

//    constructor( id: Long, name: String, translation: String) {
//        this.id = id
//        this.name = name
//        this.translation = translation
//    }
}
