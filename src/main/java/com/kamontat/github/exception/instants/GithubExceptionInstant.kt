package com.kamontat.github.exception.instants

import com.kamontat.github.exception.InvalidOrderException
import com.kamontat.github.exception.NetworkException
import com.kamontat.github.exception.constants.ErrorCode

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 07/Jul/2017 - 10:56 AM
 */
class GithubExceptionInstant {
    object Network {
        fun get(code: ErrorCode): NetworkException = NetworkException(code.toString())

        fun get(message: String): NetworkException = NetworkException(message)

        fun get(message: String, throws: Throwable): NetworkException = NetworkException(message, throws)

    }

    object Invalid {
        fun get(code: ErrorCode): InvalidOrderException = InvalidOrderException(code.toString())

        fun get(message: String): InvalidOrderException = InvalidOrderException(message)

        fun get(message: String, throws: Throwable): InvalidOrderException = InvalidOrderException(message, throws)

    }
}