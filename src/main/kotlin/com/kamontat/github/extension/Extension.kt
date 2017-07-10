package com.kamontat.github.extension

import com.beust.klaxon.JSON
import com.beust.klaxon.JsonBase
import com.beust.klaxon.Parser
import java.text.SimpleDateFormat
import java.util.*


fun JSON.fromString(string: String): JsonBase {
    return fromStringBuilder(StringBuilder(string))
}

fun JSON.fromStringBuilder(stringBuilder: StringBuilder): JsonBase {
    return Parser().parse(stringBuilder) as JsonBase
}

fun Date.githubParser(timeStamp: String): Date? {
    val format = arrayOf("yyyy/MM/dd HH:mm:ss ZZZZ", "yyyy-MM-dd'T'HH:mm:ss'Z'")
    format.forEach {
        val dateFormat = SimpleDateFormat(it)
        return dateFormat.parse(timeStamp)
    }
    return null
}