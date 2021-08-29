package com.lenakotlinvuepractice.backend.service

import com.lenakotlinvuepractice.backend.model.Word
import com.lenakotlinvuepractice.backend.repository.WordRepository
import org.hibernate.boot.model.naming.IllegalIdentifierException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface WordService {

    fun getWord(name: String?): Word?

    fun saveWord(word: Word): Word?

    fun deleteWord(name: String): Boolean

    fun findAll(): List<Word>

}

@Service
class WordServiceImpl(val  wordRepository: WordRepository) : WordService {

    override fun getWord(name: String?): Word? {
        if (name == null) {
            throw IllegalArgumentException("Word name must not be null!")
        } else {
            return wordRepository.findByName(name)
        }
    }

    override fun saveWord(word: Word): Word? {
        return wordRepository.save(word)
    }

    override fun deleteWord(name: String): Boolean {
        return wordRepository.deleteByName(name)
    }

    override fun findAll(): List<Word> {
        return wordRepository.findAll()
    }


}
