package com.kamontat.github.model.https

import com.kamontat.github.model.auth.FileLoader

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 9:25 PM
 */
class AuthorizationHeader(val token: String) : Header {
    constructor(file: FileLoader) : this(file.getByKey(FileLoader.Constants.KEY.OAUTH))

    override fun getKey(): String {
        return "Authorization"
    }

    override fun get(): Pair<String, String> {
        return getKey() to "token $token"
    }
}