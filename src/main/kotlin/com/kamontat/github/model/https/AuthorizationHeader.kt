package com.kamontat.github.model.https

import com.kamontat.github.model.auth.FileLoader

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 9:25 PM
 */
class AuthorizationHeader(val token: String) : AHeader() {
    constructor(file: FileLoader) : this(file.getByKey(FileLoader.Constants.KEY.OAUTH))
    
    override fun getValue(): String {
        return "token $token"
    }

    override fun getKey(): String {
        return "Authorization"
    }
}