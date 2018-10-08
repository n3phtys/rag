package com.github.n3phtys.rag

import com.github.ajalt.clikt.core.*
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*
import java.io.File



class RAGCommand : CliktCommand() {
    val multiplyCount: Int by option("-c","--count", help="Number of multiplications").int().default(100)
    val templateString: String? by option("-t", "--template",help="A string representation of the template in question")
    val randomSeed: Int by option("-s", "--seed", help="Random Generator Seed").int().default(13)
    val templateFile: File? by option("-i", "--input-file", help="A file with the given json template").file(exists = true, fileOkay = true, readable = true)

    override fun run() {
        val tmpl: String = if (templateFile != null) { templateFile!!.readText(Charsets.UTF_8)} else { if (templateString != null) { templateString!! } else { DEFAULT_JSON_TEMPLATE }}
        echo(RAG(template = tmpl, multiplier = multiplyCount, randomGenSeed =  randomSeed.toLong()).output)
    }
}

fun main(args: Array<String>) = RAGCommand().main(args)
