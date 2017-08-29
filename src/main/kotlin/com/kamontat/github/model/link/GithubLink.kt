package com.kamontat.github.model.link

import com.kamontat.github.annotation.ELevel
import com.kamontat.github.annotation.Level
import com.kamontat.github.annotation.Optional
import com.kamontat.github.annotation.Require
import com.kamontat.github.exception.instants.GithubExceptionInstant
import java.lang.reflect.Method
import kotlin.reflect.KClass


/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 5:22 PM
 */
open class GithubLink(protected var link: StringBuilder = StringBuilder(DEFAULT_GITHUB_URL)) {
    private var currentLevel: Level = Level.LEVEL_FIRST
    private var require: KClass<*>? = null

    companion object Factory {
        val DEFAULT_GITHUB_URL: String = "https://api.github.com/"
        fun create(build: String = ""): GithubLink = if (build == "") GithubLink() else GithubLink(StringBuilder(build))
    }

    private fun getLevel(): Level {
        val jClass = this::class.java
        if (jClass.isAnnotationPresent(ELevel::class.java))
            return jClass.getAnnotation(ELevel::class.java).level
        return Level.LEVEL_FIRST
    }

    /**
     * start with 3.. and so on
     */
    private fun getMethodName(level: Int = 3): String = Thread.currentThread().stackTrace[level].methodName

    private fun getMethod(level: Int = 5): Method {
        val name: String = getMethodName(level)
        val filterMethod = javaClass.methods.filter { method -> method.name == name }
        return filterMethod.single()
    }

    private fun manageAnnotation(level: Int = 6) {
        val method: Method = getMethod(level)
        // declared variable
        val requireAnnotation: Require? = method.getAnnotation(Require::class.java)
        val linkLevelAnnotation: ELevel? = method.getAnnotation(ELevel::class.java)
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
        currentLevel = method.getAnnotation(ELevel::class.java).level
    }

    protected fun query(message: String, level: Int = 6, addSl: Boolean = true): GithubLink {
        manageAnnotation(level)
        link.append(message)
        if (addSl)
            link.append("/")
        return this
    }

    @ELevel(Level.LEVEL_LAST)
    fun get(): String {
        manageAnnotation(4)
        return remove(1).toString()
    }

    /**
     * remove and return **new** builder
     */
    fun remove(last: Int): StringBuilder {
        val str: StringBuilder = StringBuilder(link.toString())
        return str.delete(link.length - last, link.length)
    }

    @ELevel(Level.LEVEL_2)
    fun REPOS(): GithubLink = query("repos")

    @ELevel(Level.LEVEL_2)
    @Optional(String::class)
    fun USERS(): GithubLink = query("users")

    @ELevel(Level.LEVEL_2)
    fun USER(): GithubLink = query("user")

    @ELevel(Level.LEVEL_2)
    fun AUTHORIZATIONS(): GithubLink = query("authorizations")

    @ELevel(Level.LEVEL_2)
    fun EMAILS(): GithubLink = query("emails")

    @ELevel(Level.LEVEL_2)
    fun EMOJIS(): GithubLink = query("emojis")

    @ELevel(Level.LEVEL_2)
    fun RATE_LIMIT(): GithubLink = query("rate_limit")

    @ELevel(Level.LEVEL_2)
    @Require(Int::class)
    fun GIST(): GithubLink = query("gists")

    @ELevel(Level.LEVEL_2)
    fun ISSUES(): GithubLink = query("issues")

    @ELevel(Level.LEVEL_2)
    @Optional(String::class)
    fun LICENSES(): GithubLink = query("licenses")

    @ELevel(Level.LEVEL_3)
    fun FOLLOWING(): GithubLink = query("following")

    @ELevel(Level.LEVEL_LAST)
    fun addParams(vararg pair: Pair<String, String>): GithubLink {
        link = remove(1)
        query("?", addSl = false)
        pair.forEach {
            (first, second) ->
            query("$first=$second&", addSl = false)
        }
        return this
    }

    fun setInt(int: Int) = query("$int")

    fun setString(string: String) = query(string)
}