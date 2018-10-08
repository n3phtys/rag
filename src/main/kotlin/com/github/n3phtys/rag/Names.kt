package com.github.n3phtys.rag

import java.util.*

val firstNames = listOf<String>("Klaus", "Maria", "Dieter", "Peter", "Bob", "Candy", "Anne", "Chris", "Siegfried")
val surNames = listOf<String>("Mannheim", "Müller", "Keep", "Travo", "Compton", "Veymar")

fun getCombinedName(rand: Random) : String {
    // 1-3 first names, one surname
    val numberOfFirstNames: Int = 1 + rand.nextInt(1) + rand.nextInt(1)
    val surname = surNames.get(rand.nextInt(surNames.size))
    val firstName = (0 until numberOfFirstNames).map {
        val idx = rand.nextInt(firstNames.size)
    firstNames.get(idx)
    }.joinToString(" ")
    return firstName + " " + surname
}