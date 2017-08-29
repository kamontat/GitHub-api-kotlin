package com.kamontat.github.model.link

import com.kamontat.github.model.gh.ApiUrl

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 13/Jul/2017 - 11:31 AM
 */
class LinkSeparator(message: String) {
    private val separate: List<String> = message.split(", ")
    private val next: String = separate[0]
    private val nextLink: String = decode(next)

    private val first: String = separate[1]
    private val firstLink: ApiUrl = ApiUrl(decode(first))

    private fun decode(code: String): String {
        return code.substring(code.indexOf("<") + 1, code.indexOf(">"))
    }

    fun getLink(): String {
        return ""
    }

    fun getNumber(): Int {
        return 0
    }
}