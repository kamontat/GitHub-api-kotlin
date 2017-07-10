package com.kamontat.github.model.https

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 9:30 PM
 */
abstract class AHeader : Header {
    override fun get(): Pair<String, String> {
        return getKey() to getValue()
    }

    abstract fun getValue(): String
}