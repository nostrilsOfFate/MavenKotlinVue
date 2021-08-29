package com.lenakotlinvuepractice.backend.service

import com.lenakotlinvuepractice.backend.model.Word
import com.lenakotlinvuepractice.backend.repository.WordRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class WordServiceImplTest {

    @Mock
    lateinit var wordRepository: WordRepository

    lateinit var wordService: WordService


    @Before
    fun init() {
        wordService = WordServiceImpl(wordRepository)
    }

    @Test
    fun getWord() {
        val word = Word("lake", "озеро")
        Mockito.`when`(wordRepository.findByName("lake")).thenReturn(word)
        val resultWord = wordService.getWord(word.name)
        assertEquals(word.name, resultWord?.name)
    }

    @Test
    fun saveWord() {
        val word = Word(3, "capital", "столица")
        Mockito.`when`(wordRepository.save(word)).thenReturn(word)
        val resultWord = wordService.saveWord(word)
        assertEquals(word.name, resultWord?.name)
        assertEquals(word.translation, resultWord?.translation)
    }

    @Test
    fun deleteWord() {
        val word = Word(3, "capital", "столица")

        Mockito.`when`(wordRepository.deleteByName(word.name)).thenReturn(true)
        val result = wordService.deleteWord("capital")
        assertEquals(true, result)
    }

    @Test
    fun findAll() {
        val listWords = listOf<Word>(Word(1, "blue", "голубой"), Word(2, "lake", "озеро"))

        Mockito.`when`(wordRepository.findAll()).thenReturn(listWords)
        val resultList = wordService.findAll()
        assertEquals(listWords.size, resultList.size)
        assertEquals(listWords.toSet(), resultList.toSet())
    }
}