package com.kamontat.github.raw

import com.kamontat.github.exception.GithubException
import com.kamontat.github.exception.constants.ErrorCode
import com.kamontat.github.exception.instants.GithubExceptionInstant
import com.kamontat.github.raw.option.CurlOption
import java.io.InputStream
import java.net.URL
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 04/Jul/2017 - 3:34 PM
 */
class CURL(private val rawLink: String, vararg arg: CurlOption) {
    val link: URL
        get() = URL(rawLink)

    val inputStream: InputStream
        get() = link.openStream()

    @Throws(GithubException::class)
    private fun fetch() {
        if (Objects.isNull(link) || Objects.isNull(inputStream))
            throw GithubExceptionInstant.Network.get(ErrorCode.LINK_NOT_FOUND)

    }
}