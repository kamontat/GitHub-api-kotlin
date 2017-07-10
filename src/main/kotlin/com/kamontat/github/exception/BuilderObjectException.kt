package com.kamontat.github.exception

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 3:51 PM
 */
class BuilderObjectException(message: String?, cause: Throwable?) : GithubException(message, cause) {
    constructor(message: String?) : this(message, null)

    override var reason: String? = "cannot build object"
}