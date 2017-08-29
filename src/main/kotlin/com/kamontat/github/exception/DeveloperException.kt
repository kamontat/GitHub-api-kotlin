package com.kamontat.github.exception

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 13/Jul/2017 - 3:31 PM
 */
class DeveloperException(message: String?, cause: Throwable?) : GithubException(message, cause) {
    constructor(message: String?) : this(message, null)

    override var reason: String? = "Wrong because Developer Error"
}