package com.lenakotlinvuepractice.backend.model

import javax.persistence.*

@Entity
@Table(name = "example")
class Example(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "text")
        var text: String,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "example_words",
                joinColumns = [JoinColumn(name = "example_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "word_id", referencedColumnName = "id")]
        )
        var words: Collection<Word>? = null
) {
        constructor( text: String, words: Collection<Word>?): this(1, text, words) {
                this.text = text
                this.words = words
        }

}