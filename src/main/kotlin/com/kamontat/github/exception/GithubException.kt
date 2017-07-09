package com.kamontat.github.exception

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 4:57 PM
 */
abstract class GithubException(message: String?, cause: Throwable?) : Exception(message, cause) {
    open var reason: String? = null
}