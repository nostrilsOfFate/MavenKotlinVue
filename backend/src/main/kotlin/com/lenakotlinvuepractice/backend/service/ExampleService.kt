package com.lenakotlinvuepractice.backend.service

import com.lenakotlinvuepractice.backend.model.Example
import com.lenakotlinvuepractice.backend.repository.ExampleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface ExampleService {

    fun getExample(text: String?): Example?

    fun saveExample(example: Example): Example?

    fun deleteExample(text: String): Boolean

    fun findAll(): List<Example>

}

@Service
class ExampleServiceImpl(val exampleRepository: ExampleRepository) : ExampleService {


    override fun getExample(text: String?): Example? {
        if (text == null) {
            throw IllegalArgumentException("Example text must not be null!")
        } else {
            return exampleRepository.findByText(text)
        }
    }

    override fun saveExample(example: Example): Example? {
        return exampleRepository.save(example)
    }

    override fun deleteExample(text: String): Boolean {
        return exampleRepository.deleteByText(text)

    }

    override fun findAll(): List<Example> {
        return exampleRepository.findAll()
    }


}