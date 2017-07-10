package com.kamontat.github.model.https

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 9:17 PM
 */
interface Header {
    fun getKey(): String

    fun get(): Pair<String, String>
}