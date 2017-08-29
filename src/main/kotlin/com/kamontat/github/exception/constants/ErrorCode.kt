package com.kamontat.github.exception.constants

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 5:05 PM
 */
enum class ErrorCode(private val code: String, private val message: String) {
    // hard to happen
    LINK_NOT_FOUND("N1000", "Link not found"),
    // developer failure
    NEVER_BUILD_LINK("D2000", "You never build link"),
    CONSTRUCTOR_NOT_EXIST("D2001", "This class don't have primary constructor"),
    SIZE_NOT_MATCHES("D2005", "array size not match"),
    /**
     * mostly this error happen when you use wrong method to build the object
     */
    WRONG_PARAMETER("D1000", "wrong state or type in build parameter"),
    WRONG_JSON_KEY("D2000", "json key annotation is wrong by some reason"),
    // server failure
    RESPONSE_NOT_FOUND("S1000", "server not sent any response message")
    ;

    override fun toString(): String {
        return "$message ($code)"
    }
}