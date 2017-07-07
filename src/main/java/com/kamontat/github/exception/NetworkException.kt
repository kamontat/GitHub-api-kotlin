package com.kamontat.github.exception

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 5:01 PM
 */
class NetworkException(message: String?, cause: Throwable?) : GithubException(message, cause) {
    constructor(message: String?) : this(message, null)

    override var reason: String? = "Network connection failed"
}