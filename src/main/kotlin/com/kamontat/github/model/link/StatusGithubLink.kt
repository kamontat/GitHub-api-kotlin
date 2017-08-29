package com.kamontat.github.model.link

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 13/Jul/2017 - 10:51 AM
 */
class StatusGithubLink : GithubLink(StringBuilder("https://status.github.com/api/")) {
    fun CURRENT_STATUS(): StatusGithubLink = query("status.json") as StatusGithubLink

    fun LATEST_STATUS(): StatusGithubLink = query("last-message.json") as StatusGithubLink

    fun MOST_RECENT_STATUS(): StatusGithubLink = query("messages.json") as StatusGithubLink
}