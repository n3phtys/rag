package com.github.n3phtys.rag

import com.google.gson.*
import java.lang.UnsupportedOperationException
import java.util.*


val DEFAULT_JSON_TEMPLATE = "[{\"name\":\"\$name\",\"id\":\"\$id_int\"}]"
val DEFAULT_MULTIPLIER_RATE = 3
val PARAMETER_DELIMITER = "$"

class RAG(val template: String = DEFAULT_JSON_TEMPLATE, val multiplier: Int = DEFAULT_MULTIPLIER_RATE, randomGenSeed: Long = 42) {

    private val rand = Random(randomGenSeed)

    private val templateJson = JsonParser().parse(template)!!

    private val idsUsed = mutableMapOf<String, MutableSet<Int>>()

    val output = generateOutput()



    private fun generateOutput() : String {
        return processElementRecursively(templateJson).toString()
    }


    private fun getNewRandomId(idType: String) : Int {
        val used = idsUsed.computeIfAbsent(idType) { mutableSetOf()}
        var id: Int
        do {
            id = Math.abs(rand.nextInt())
        } while (used.contains(id))
        used.add(id)
        return id
    }


    private fun processElementRecursively(element : JsonElement) : JsonElement {
        if (element.isJsonPrimitive) {
            //case A: is basic field -> process and return
            return this.processBasicField(element.asJsonPrimitive)
        } else if (element.isJsonArray) {
            //case B: is array -> get inner and multiply a given number of times recursively before returning
            return this.processArray(element.asJsonArray)
        } else if (element.isJsonNull) {
            return element.deepCopy()
        } else if (element.isJsonObject) {
            //case C: is object -> iterate over fields and process them recursively before returning
            return this.processObject(element.asJsonObject)
        } else {
            throw UnsupportedOperationException()
        }
    }

    private fun processBasicField(field: JsonPrimitive) : JsonPrimitive {
        if (!field.isString) {
            return field.deepCopy()
        } else {
            val str = field.asString
            if (!str.contains(PARAMETER_DELIMITER)) {
                return field.deepCopy()
            } else {
                //iterate through given replacements
                val sub = str.substringAfter(PARAMETER_DELIMITER)
                // TODO: placeholders should be extract in place and hard length limited
                if ("name".equals(sub)) {
                    return JsonPrimitive(getCombinedName(rand))
                } else if ( sub.startsWith("id_int")) {
                    val idType = sub.substringAfter("id_int_", "id_int_default")
                    return JsonPrimitive(getNewRandomId(idType))
                } else if (sub.startsWith("id_str")){
                    val idType = sub.substringAfter("id_str_", "id_str_default")
                    return JsonPrimitive(getNewRandomId(idType).toString())
                } else {
                    throw UnsupportedOperationException()
                }
            }
        }
    }

    private fun processArray(array: JsonArray) : JsonArray {
        val newArray = JsonArray()
        (0 until multiplier).flatMap { _ -> array.map { jsonElement ->  processElementRecursively(jsonElement)  }  }.forEach {
            newArray.add(it)
        }
        return newArray
    }

    private fun processObject(obj: JsonObject) : JsonObject {
        val newObject = JsonObject()
        obj.entrySet().forEach {
            newObject.add(it.key!!, processElementRecursively(it.value!!))
        }
        return newObject
    }

}