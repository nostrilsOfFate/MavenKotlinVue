package com.lenakotlinvuepractice.backend.controller

import com.lenakotlinvuepractice.backend.model.ResponseMessage
import com.lenakotlinvuepractice.backend.model.Word
import com.lenakotlinvuepractice.backend.service.ExampleService
import com.lenakotlinvuepractice.backend.service.WordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/word")
class WordController() {
    @Autowired
    lateinit var wordService: WordService

    @Autowired
    lateinit var exampleService: ExampleService

    @RequestMapping("/")
    fun index() = "This is your app!"

    @GetMapping("/words")
    fun getWords(): ResponseEntity<*> {

        val words: List<Word> = wordService.findAll()

        return if (words.isNotEmpty()) {
            ResponseEntity.ok(words)
        } else {
            ResponseEntity(ResponseMessage("Empty list!"),
                    HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping
    fun createWord(@Valid @RequestBody name: String, @RequestBody translation: String): ResponseEntity<Word> {
        val word = wordService.saveWord(Word(name, translation))
        return ResponseEntity.status(HttpStatus.OK).body(word)
    }

    @PostMapping
    fun deleteWord(@Valid @RequestBody name: String): ResponseEntity<Boolean> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(wordService.deleteWord(name))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(false)
        }
    }

    @GetMapping("/{name}")
    fun getWord(@PathVariable name: String): ResponseEntity<Word?> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(wordService.getWord(name))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @GetMapping("/{name}")
    fun getExample(@PathVariable name: String): ResponseEntity<Word?> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(wordService.getWord(name))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

//    @GetMapping("/{name}")
//    fun getAllExample(@PathVariable name: String): ResponseEntity<Word?> {
//        return try {
//            ResponseEntity.status(HttpStatus.OK).body(exampleService.findAll())
//        } catch (e: Exception) {
//            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
//        }
//    }

}