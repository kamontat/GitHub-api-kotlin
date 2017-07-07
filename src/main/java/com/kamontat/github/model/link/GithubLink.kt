package com.kamontat.github.model.link

import com.kamontat.github.annotation.Level
import com.kamontat.github.annotation.LinkLevel
import com.kamontat.github.annotation.Require
import com.kamontat.github.exception.instants.GithubExceptionInstant
import java.lang.reflect.Method
import kotlin.reflect.KClass


/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 5:22 PM
 */
class GithubLink(private var link: StringBuilder = StringBuilder("https://api.github.com/")) {
    private var currentLevel: Level = Level.LEVEL_FIRST
    private var require: KClass<*>? = null

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

    private fun getMethod(level: Int = 5): Method? {
        val name: String = getMethodName(level)
        val filterMethod = javaClass.methods.filter { method -> method.name == name }
        return filterMethod.singleOrNull()
    }

    private fun query(message: String): GithubLink {
        manageAnnotation()
        link.append(message).append("/")
        return this
    }

    private fun manageAnnotation(level: Int = 6) {
        val method: Method = getMethod(level)!!
        // declared variable
        val requireAnnotation: Require? = method.getAnnotation(Require::class.java)
        val linkLevelAnnotation: LinkLevel? = method.getAnnotation(LinkLevel::class.java)
        // check require
        if (require != null)
            if (method.parameterCount != 1 || method.parameters[0].type != require?.java)
                throw GithubExceptionInstant.Invalid.get("require set \"${require.toString().split(" (")[0]}\"")

        // set required (if exist)
        require = requireAnnotation?.nextClass

        if (linkLevelAnnotation == null) return
        // check link level
        if (!currentLevel.compare(linkLevelAnnotation.level)) throw GithubExceptionInstant.Invalid.get("request method currentLevel (at ${method.name}())")
        // set new level
        currentLevel = method.getAnnotation(LinkLevel::class.java).level
    }

    @LinkLevel(Level.LEVEL_LAST)
    fun get(): String {
        manageAnnotation(4)
        return link.deleteCharAt(link.length - 1).toString()
    }

    @LinkLevel(Level.LEVEL_2)
    fun REPOS(): GithubLink = query("repos")

    @LinkLevel(Level.LEVEL_2)
    fun USERS(): GithubLink = query("users")

    @LinkLevel(Level.LEVEL_2)
    fun USER(): GithubLink = query("user")

    @LinkLevel(Level.LEVEL_2)
    fun AUTHORIZATIONS(): GithubLink = query("authorizations")

    @LinkLevel(Level.LEVEL_2)
    fun EMAILS(): GithubLink = query("emails")

    @LinkLevel(Level.LEVEL_2)
    fun EMOJIS(): GithubLink = query("emojis")

    @LinkLevel(Level.LEVEL_2)
    fun RATE_LIMIT(): GithubLink = query("rate_limit")

    @LinkLevel(Level.LEVEL_2)
    @Require(Int::class)
    fun GIST(): GithubLink = query("gists")

    @LinkLevel(Level.LEVEL_2)
    fun ISSUES(): GithubLink = query("issues")

    @LinkLevel(Level.LEVEL_3)
    fun KEYS(): GithubLink = query("keys")

    fun setInt(int: Int) = query("$int")

    fun setString(string: String) = query(string)
}