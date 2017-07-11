package com.kamontat.github.model.link

import com.kamontat.github.annotation.ELevel
import com.kamontat.github.annotation.Level
import com.kamontat.github.annotation.Require
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 11/Jul/2017 - 11:58 AM
 */
class SearchGithubLink : GithubLink(StringBuilder("${DEFAULT_GITHUB_URL}search/")) {
    companion object Factory {
        fun create(): SearchGithubLink = SearchGithubLink()
    }

    @ELevel(Level.LEVEL_2)
    fun REPOSITORIES(): SearchGithubLink = query("repositories") as SearchGithubLink

    @ELevel(Level.LEVEL_2)
    fun COMMITS(): SearchGithubLink = query("commits") as SearchGithubLink

    @ELevel(Level.LEVEL_2)
    fun CODE(): SearchGithubLink = query("code") as SearchGithubLink

    @ELevel(Level.LEVEL_2)
    @Require(String::class)
    fun KEYS(): SearchGithubLink = query("keys") as SearchGithubLink

    fun addQuery(vararg querys: Pair<Keywords, String>): SearchGithubLink {
        val builder = StringBuilder()
        querys.forEach {
            if (it.first == Keywords.DEFAULT)
                builder.append("${it.second}+")
            else
                builder.append("${it.first}:${it.second}+")
        }
        addParams("q" to builder.toString())
        link = remove(1)
        return this
    }
}

enum class Keywords {
    // repository
    R_CREATED,
    R_PUSHED,
    R_FORK,
    R_FORKS,
    R_IN,
    R_LANGUAGE,
    R_REPO,
    R_USER,
    R_SIZE,
    R_STARS,
    R_TOPIC,
    // commit
    C_AUTHOR,
    C_COMMITTER,
    C_AUTHOR_EMAIL,
    C_COMMITTER_EMAIL,
    C_AUTHOR_DATE,
    C_COMMITTER_DATE,
    C_MERGE,
    C_HASH,
    C_PARENT,
    C_TREE,
    C_IS,
    C_ORG,
    // code
    O_IN,
    O_LANGUAGE,
    O_FORK,
    O_SIZE,
    O_PATH,
    O_FILENAME,
    O_EXTENSION,
    // all
    DEFAULT,
    REPO,
    USER
    ;

    override fun toString(): String {
        return name.substring(2).replace("_", "-").toLowerCase(Locale.ENGLISH)
    }
}