package com.kamontat.github

import com.kamontat.github.extension.toJSON
import com.kamontat.github.model.auth.FileLoader
import com.kamontat.github.model.gh.GHObjectBuilder
import com.kamontat.github.model.gh.ResourceRateLimit
import com.kamontat.github.model.https.AcceptHeader
import com.kamontat.github.model.https.AuthorizationHeader
import com.kamontat.github.model.https.CURL
import com.kamontat.github.model.https.RequestMethod
import com.kamontat.github.model.link.GithubLink

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 6:09 PM
 */
fun main(args: Array<String>) {
    val link: GithubLink = GithubLink.create()
            .RATE_LIMIT()

    val response = CURL(RequestMethod.GET, link)
            .header(AcceptHeader.LICENSE)
            .header(AuthorizationHeader(FileLoader()))
            .fetch()
            .toJSON()

    // print(response.toJsonString(true))

    print(GHObjectBuilder.build(ResourceRateLimit::class, response).toString())
}