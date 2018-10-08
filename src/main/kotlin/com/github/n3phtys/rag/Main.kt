package com.github.n3phtys.rag

import com.github.ajalt.clikt.core.*
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*
import java.io.File



class RAGCommand : CliktCommand() {
    val multiplyCount: Int by option("-c","--count", help="Number of multiplications").int().default(100)
    val templateString: String? by option("-t", "--template",help="A string representation of the template in question")
    val randomSeed: Long by option("-s", "--seed", help="Random Generator Seed").long().default(13)
    val templateFile: File? by option("-i", "--input-file", help="A file with the given json template").file(exists = true, fileOkay = true, readable = true)

    override fun run() {
        //TODO: take alternatively from stdin
        val tmpl: String = if (templateFile != null) { templateFile!!.readText(Charsets.UTF_8)} else { if (templateString != null) { templateString!! } else { throw MissingParameter("Either --template or --input-file has to be provided") }}
        echo(RAG(template = tmpl, multiplier = multiplyCount, randomGenSeed =  randomSeed).output)
    }
}

fun main(args: Array<String>) = RAGCommand().main(args)
