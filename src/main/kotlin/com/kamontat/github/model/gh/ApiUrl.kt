package com.kamontat.github.model.gh

import com.kamontat.github.exception.constants.ErrorCode
import com.kamontat.github.exception.instants.GithubExceptionInstant
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 13/Jul/2017 - 3:28 PM
 */
open class ApiUrl(vararg protected val links: String?) : GObject() {
    private val size = links.size

    private val params = ArrayList<String>()
    private val paths = ArrayList<String>()

    fun <T : ApiUrl> cast(tClass: KClass<T>): T {
        return tClass.cast(this)
    }


    fun getLink(index: Int): String {
        return decode(index)
    }

    @Throws(NullPointerException::class)
    protected fun decode(index: Int): String {
        if (index >= size) throw GithubExceptionInstant.DeveloperError.get(ErrorCode.SIZE_NOT_MATCHES)
        val link: String = links[index] ?: throw GithubExceptionInstant.Common.get(NullPointerException::class, "link not found")

        val getter = Regex("""\{.*}""")
        return when {
            Regex(""".*\{.*}""").matches(link) -> {
                val decodes: Sequence<MatchResult> = getter.findAll(link)
                decodes.forEach {
                    matchResult ->
                    run {
                        val value: String = matchResult.value.substring(1, matchResult.value.length - 1)
                        when {
                            value.startsWith("/") -> return@run paths.add(value)
                            value.startsWith("?") -> return@run params.add(value)
                            else -> return@run false
                        }
                    }
                }
                getter.replace(link, "")
            }
            else -> link
        }
    }
}