package com.kamontat.github.model.https

import com.beust.klaxon.JsonBase
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.kamontat.github.exception.GithubException
import com.kamontat.github.exception.constants.ErrorCode
import com.kamontat.github.exception.instants.GithubExceptionInstant
import com.kamontat.github.extension.fromString
import com.kamontat.github.model.link.GithubLink
import com.kamontat.github.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.net.URL
import java.util.*
import java.util.logging.Level

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 04/Jul/2017 - 3:34 PM
 */
class CURL(private val method: RequestMethod, private val rawLink: GithubLink, private val debug: Boolean = false) {
    private val logger = Log(debug)

    private var link: URL = URL(rawLink.get())

    init {
        logger.get()?.log(Level.INFO, "link: $link")
    }


    private val client = OkHttpClient()
    private var request: Request? = null

    private val requestBuilder: Request.Builder = Request.Builder().method(method.name, null)

    companion object {
        val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
    }

    @Throws(GithubException::class)
    fun fetch(): String {
        logger.get()?.log(Level.INFO, "start fetch ($link)")
        request = requestBuilder.url(link).build()

        if (!isBuild())
            throw GithubExceptionInstant.Invalid.get(ErrorCode.NEVER_BUILD_LINK)
        if (Objects.isNull(link) || Objects.isNull(request))
            throw GithubExceptionInstant.Network.get(ErrorCode.LINK_NOT_FOUND)

        val response = client.newCall(request).execute()

        if (Objects.isNull(response) && Objects.isNull(response.body()))
            throw GithubExceptionInstant.Network.get(ErrorCode.RESPONSE_NOT_FOUND)
        if (!response.isSuccessful)
            throw GithubExceptionInstant.ErrorMessage.get(Parser().parse(StringBuilder(response.body()!!.string())) as JsonObject, response.code())

        return response.body()!!.string()
    }

    @Throws(GithubException::class)
    fun fetchAsJSON(): JsonBase {
        return com.beust.klaxon.JSON().fromString(fetch())
    }

    fun header(pair: Pair<String, String>): CURL {
        requestBuilder.header(pair.first, pair.second)
        return this
    }

    fun body(json: JsonObject): CURL {
        requestBuilder.method(method.name, RequestBody.create(JSON, json.toJsonString(true)))
        return this
    }

    private fun isBuild(): Boolean {
        return Objects.nonNull(request)
    }
}