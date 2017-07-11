package com.kamontat.github.model.https

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.kamontat.github.exception.GithubException
import com.kamontat.github.exception.constants.ErrorCode
import com.kamontat.github.exception.instants.GithubExceptionInstant
import com.kamontat.github.model.link.GithubLink
import com.kamontat.github.util.Log
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 04/Jul/2017 - 3:34 PM
 */
class CURL(private val method: RequestMethod, rawLink: GithubLink, debug: Boolean = false) {
    private val logger: Logger? = Log.create()

    private var link: URL = URL(rawLink.get())

    init {
        logger?.log(Level.INFO, "link: $link")
    }


    private val client = OkHttpClient()
    private var request: Request? = null

    private val requestBuilder: Request.Builder = Request.Builder().method(method.name, null)

    companion object {
        val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
    }

    @Throws(GithubException::class)
    fun fetch(): Response {
        logger?.log(Level.INFO, "start fetch ($link)")
        request = requestBuilder.url(link).build()

        if (!isBuild())
            throw GithubExceptionInstant.Invalid.get(ErrorCode.NEVER_BUILD_LINK)
        if (Objects.isNull(link) || Objects.isNull(request))
            throw GithubExceptionInstant.Network.get(ErrorCode.LINK_NOT_FOUND)

        try {
            val response: Response = client.newCall(request).execute()

            if (Objects.isNull(response) || Objects.isNull(response.body()))
                throw GithubExceptionInstant.Network.get(ErrorCode.RESPONSE_NOT_FOUND)
            if (!response.isSuccessful)
                throw GithubExceptionInstant.ErrorMessage.get(link.toString(), Parser().parse(StringBuilder(response.body()!!.string())) as JsonObject, response.code())

            return response
        } catch (e: IOException) {
            throw GithubExceptionInstant.Network.get(e.localizedMessage, e)
        } catch (e: IllegalStateException) {
            throw GithubExceptionInstant.Network.get(e.localizedMessage, e)
        }
    }

    fun header(header: Header): CURL {
        requestBuilder.header(header.get().first, header.get().second)
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