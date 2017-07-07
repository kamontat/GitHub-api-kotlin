package com.kamontat.github.model.link

import com.kamontat.github.annotation.Level
import com.kamontat.github.annotation.LinkLevel
import com.kamontat.github.exception.instants.GithubExceptionInstant
import java.lang.reflect.Method


/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 5:22 PM
 */
class GithubLink(private var link: StringBuilder = StringBuilder("https://api.github.com/")) {
    private var level: Level = Level.LEVEL_FIRST

    companion object Factory {
        fun create(): GithubLink = GithubLink()
    }

    private fun getLevel(): Level {
        val jClass = this::class.java
        if (jClass.isAnnotationPresent(LinkLevel::class.java))
            return jClass.getAnnotation(LinkLevel::class.java).level
        return Level.LEVEL_FIRST
    }

    /**
     * start with 3.. and so on
     */
    private fun getMethodName(level: Int = 3): String = Thread.currentThread().stackTrace[level].methodName

    private fun getMethod(level: Int = 5): Method = javaClass.getMethod(getMethodName(level))

    private fun query(message: String): GithubLink {
        val method = getMethod()
        val temp: Level = method.getAnnotation(LinkLevel::class.java).level
        if (!temp.compare(level)) throw GithubExceptionInstant.Invalid.get("request method level (at ${method.name}())")
        level = getMethod().getAnnotation(LinkLevel::class.java).level

        link.append(message)
        return this
    }

    @LinkLevel(Level.LEVEL_LAST)
    fun get(): String = link.toString()

    @LinkLevel(Level.LEVEL_2)
    fun REPOS(): GithubLink = query("repos/")

    @LinkLevel(Level.LEVEL_2)
    fun USERS(): GithubLink = query("users/")

    @LinkLevel(Level.LEVEL_2)
    fun USER(): GithubLink = query("user/")

    @LinkLevel(Level.LEVEL_2)
    fun AUTHORIZATIONS(): GithubLink = query("authorizations/")

    @LinkLevel(Level.LEVEL_2)
    fun EMAILS(): GithubLink = query("emails/")

    @LinkLevel(Level.LEVEL_2)
    fun EMOJIS(): GithubLink = query("emojis/")

    @LinkLevel(Level.LEVEL_2)
    fun RATE_LIMIT(): GithubLink = query("rate_limit/")
}