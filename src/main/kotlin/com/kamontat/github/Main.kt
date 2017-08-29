package com.kamontat.github

import com.kamontat.github.model.gh.ApiUrl

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 6:09 PM
 */
fun main(args: Array<String>) {
    // val link: GithubLink = GithubLink.create()
    //         .USERS()

    // val status: GithubLink = StatusGithubLink().LATEST_STATUS()

    // val response = CURL(RequestMethod.GET, link)
    //         .header(AcceptHeader.LICENSE)
    //         .header(AuthorizationHeader(FileLoader()))
    //         .fetch()
    //         .next()

    print(ApiUrl("https://api.github.com/users{/since}").getLink(0))

    // print(response.toJsonString(true))
    // print(GHObjectBuilder.build(User::class, (response as JsonArray<JsonObject>)[0]))
}