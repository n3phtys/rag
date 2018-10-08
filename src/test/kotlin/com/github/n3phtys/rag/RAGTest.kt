package com.github.n3phtys.rag

import org.junit.Assert.*
import org.junit.Test

class RAGTest {


    val DEFAULT_JSON_OUTPUT_1 = "[{\"name\":\"Siegfried Mannheim\",\"id\":1325939940}]"
    val DEFAULT_JSON_OUTPUT_2 = "[{\"name\":\"Siegfried Mannheim\",\"id\":1325939940},{\"name\":\"Chris Keep\",\"id\":392236186}]"

    @Test
    fun defaultJsonIsMultipliedOnce() {
        val rag = com.github.n3phtys.rag.RAG(multiplier = 1)
        assertEquals(DEFAULT_JSON_OUTPUT_1, rag.output);
    }

    @Test
    fun defaultJsonIsMultipliedTwice() {
        val rag = com.github.n3phtys.rag.RAG(multiplier = 2)
        assertEquals(DEFAULT_JSON_OUTPUT_2, rag.output);
    }

    @Test
    fun jsonWithoutArrayIsNotMultiplied() {
        val template = "{\"id\":\"\$id_str_my_type\"}"
        val output = "{\"id\":\"1170105035\"}"
        val rag = com.github.n3phtys.rag.RAG(template = template, multiplier = 2)
        assertEquals(output, rag.output);
    }
}