package com.github.n3phtys.rag

import com.github.ajalt.clikt.core.*
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*


class Hello : CliktCommand() {
    val count: Int by option(help="Number of greetings").int().default(1)
    val xanyarg : Int? by option(help="Some unused int argument").int()
    val name: String by option(help="The person to greet").default("n3phtys")

    override fun run() {
        for (i in 1..count) {
            echo("Hello $name!")
        }
    }
}

fun main(args: Array<String>) = Hello().main(args)









fun helloWorld(): String {
    return "Hello World!"
}