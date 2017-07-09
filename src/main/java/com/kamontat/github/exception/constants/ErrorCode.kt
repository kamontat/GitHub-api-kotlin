package com.kamontat.github.exception.constants

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 5:05 PM
 */
enum class ErrorCode(private val code: String, private val message: String) {
    LINK_NOT_FOUND("N1000", "Link not found"),
    NEVER_BUILD_LINK("N2000", "You never build link"),
    RESPONSE_NOT_FOUND("F1000", "server not sent any response message")
    ;

    override fun toString(): String {
        return "$message ($code)"
    }
}