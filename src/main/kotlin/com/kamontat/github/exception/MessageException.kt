package com.kamontat.github.exception

import com.beust.klaxon.JsonObject
import com.beust.klaxon.string
import com.kamontat.github.exception.constants.HttpCode

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 08/Jul/2017 - 8:23 AM
 */
class MessageException(message: String?, code: HttpCode, throwable: Throwable?) : GithubException("$message\n response code: $code.", throwable) {
    constructor(json: JsonObject, code: HttpCode) : this(json.string("message"), code, null)

    constructor(json: JsonObject, code: HttpCode, throwable: Throwable?) : this(json.string("message"), code, throwable)

    constructor(message: String, code: HttpCode) : this(message, code, null)

    override var reason: String? = "api error message"
}