package com.kamontat.github

import com.kamontat.github.model.https.CURL
import com.kamontat.github.model.https.RequestMethod
import com.kamontat.github.model.link.GithubLink
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 6:09 PM
 */
fun main(args: Array<String>) {
    val link: GithubLink = GithubLink.create()
            .USER()

    val response = CURL(RequestMethod.GET, link).header(GithubLink.ACCEPT_HEADER).header(GithubLink.AUTHORIZATION_HEADER("bae2038082c5bf0d9f3856daedd1510f18adf6df")).fetchAsJSON()

    print(response.toJsonString(true))

    // val loader = FileLoader()
    // loader.load()
    // loader.newOAUTH("bae2038082c5bf0d9f3856daedd1510f18adf6df")
    // loader.save()
}

fun prints(strings: Array<String>) {
    print(Arrays.toString(strings))
}