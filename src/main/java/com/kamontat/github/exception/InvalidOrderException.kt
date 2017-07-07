package com.kamontat.github.exception

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 07/Jul/2017 - 10:15 AM
 */
class InvalidOrderException(message: String?, cause: Throwable?) : GithubException(message, cause) {
    constructor(message: String?) : this(message, null)

    override var reason: String? = "Invalid order sequence"
}