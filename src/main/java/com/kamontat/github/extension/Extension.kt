package com.kamontat.github.extension

import com.beust.klaxon.JSON
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

fun JSON.fromString(string: String): JsonObject {
    return Parser().parse(StringBuilder(string)) as JsonObject
}

fun JSON.fromStringBuilder(stringBuilder: StringBuilder): JsonObject {
    return Parser().parse(stringBuilder) as JsonObject
}