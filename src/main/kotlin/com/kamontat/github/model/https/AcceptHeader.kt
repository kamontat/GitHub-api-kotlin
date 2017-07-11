package com.kamontat.github.model.https

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 9:18 PM
 */
enum class AcceptHeader : Header {
    JSON() {
        override fun get(): Pair<String, String> {
            return getKey() to "application/vnd.github.v3+json"
        }
    },
    LICENSE() {
        override fun get(): Pair<String, String> {
            return getKey() to "application/vnd.github.drax-preview+json"
        }
    },
    SEARCH_REPOSITORY() {
        override fun get(): Pair<String, String> {
            return getKey() to "application/vnd.github.mercy-preview+json"
        }
    },
    SEARCH_COMMIT() {
        override fun get(): Pair<String, String> {
            return getKey() to "application/vnd.github.cloak-preview"
        }
    }
    ;

    override fun getKey(): String {
        return "Accept"
    }
}