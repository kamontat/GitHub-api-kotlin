package com.kamontat.github.exception

import com.beust.klaxon.JsonObject
import com.kamontat.github.exception.constants.HttpCode

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 08/Jul/2017 - 8:23 AM
 */
class MessageException(link: String, message: String?, code: HttpCode, throwable: Throwable?) : GithubException("\n-> link: $link \n-> message: $message \n-> response code: $code.", throwable) {
    constructor(link: String, json: JsonObject, code: HttpCode) : this(link, json.toJsonString(true), code, null)

    constructor(link: String, json: JsonObject, code: HttpCode, throwable: Throwable?) : this(link, json.toJsonString(true), code, throwable)

    constructor(link: String, message: String, code: HttpCode) : this(link, message, code, null)

    override var reason: String? = "api error message"
}