package com.kamontat.github.exception.constants

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 5:05 PM
 */
enum class ErrorCode(private val code: String, private val message: String) {
    LINK_NOT_FOUND("N1000", "Link not found");


    override fun toString(): String {
        return "$message ($code)"
    }
}