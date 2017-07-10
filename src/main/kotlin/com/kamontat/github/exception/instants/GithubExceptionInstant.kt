package com.kamontat.github.exception.instants

import com.beust.klaxon.JsonObject
import com.kamontat.github.exception.BuilderObjectException
import com.kamontat.github.exception.InvalidOrderException
import com.kamontat.github.exception.MessageException
import com.kamontat.github.exception.NetworkException
import com.kamontat.github.exception.constants.ErrorCode
import com.kamontat.github.exception.constants.HttpCode

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

    object ErrorMessage {
        fun get(message: String, code: HttpCode, throws: Throwable? = null): MessageException = MessageException(message, code, throws)

        fun get(message: String, code: Int, throws: Throwable? = null): MessageException = MessageException(message, HttpCode.getByCode(code)[0], throws)

        fun get(json: JsonObject, code: Int, throws: Throwable? = null): MessageException = MessageException(json, HttpCode.getByCode(code)[0], throws)

        fun get(json: JsonObject, code: HttpCode, throws: Throwable? = null): MessageException = MessageException(json, code, throws)
    }

    object BuilderError {
        fun get(code: ErrorCode): BuilderObjectException = BuilderObjectException(code.toString())

        fun get(code: ErrorCode, throws: Throwable): BuilderObjectException = BuilderObjectException(code.toString(), throws)

        fun get(message: String): BuilderObjectException = BuilderObjectException(message)

        fun get(message: String, throws: Throwable): BuilderObjectException = BuilderObjectException(message, throws)
    }
}