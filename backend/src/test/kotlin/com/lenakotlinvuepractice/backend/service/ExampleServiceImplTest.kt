package com.lenakotlinvuepractice.backend.service

import com.lenakotlinvuepractice.backend.model.Example
import com.lenakotlinvuepractice.backend.model.Word
import com.lenakotlinvuepractice.backend.repository.ExampleRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ExampleServiceImplTest {

    @Mock
    lateinit var exampleRepository: ExampleRepository

    lateinit var exampleService: ExampleService



    @Before
    fun init(){
        exampleService = ExampleServiceImpl(exampleRepository)
    }

    @Test
    fun getExample() {
        val example = Example(1,"Blue lake", listOf(Word( "blue", "голубой"),Word("lake", "озеро")))
        Mockito.`when`(exampleRepository.findByText("Blue lake")).thenReturn(example)
        val resultExample = exampleService.getExample(example.text)
        assertEquals(example.text, resultExample?.text)
    }

    @Test
    fun saveExample() {
        val example = Example(1,"Blue lake", listOf(Word( "blue", "голубой"),Word("lake", "озеро")))
        Mockito.`when`(exampleRepository.save(example)).thenReturn(example)
        val resultExample = exampleService.saveExample(example)
        assertEquals(example.text, resultExample?.text)
        assertEquals(example.words?.toSet(), resultExample?.words?.toSet())
    }

    @Test
    fun deleteExample() {
        val example = Example(1,"Blue lake", listOf(Word( "blue", "голубой"),Word("lake", "озеро")))

        Mockito.`when`(exampleRepository.deleteByText(example.text)).thenReturn(true)
        val result = exampleService.deleteExample("Blue lake")
        assertEquals(true, result)
    }

    @Test
    fun findAll() {
        val example1 = Example(1,"Blue lake", listOf(Word( "blue", "голубой"),Word("lake", "озеро")))
        val example2 = Example(2,"Grey sky", listOf(Word( "gray", "серый"),Word("sky", "небо")))
        val listExamples = listOf<Example>(example1, example2)

        Mockito.`when`(exampleRepository.findAll()).thenReturn(listExamples)
        val resultList = exampleService.findAll()
        assertEquals(listExamples.size, resultList.size)
        assertEquals(listExamples.toSet(), resultList.toSet())
    }







}