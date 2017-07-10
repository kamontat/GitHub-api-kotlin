package com.kamontat.github.model.gh

import com.kamontat.github.annotation.JsonKey
import java.time.Instant
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 9:41 PM
 */
data class RateLimit(
        @JsonKey("limit") val limit: Int,
        @JsonKey("remaining") val remaining: Int,
        @JsonKey("reset") private val rawReset: Long
) : GObject() {
    val resetDateTime: Date = Date.from(Instant.ofEpochMilli(rawReset * 1000L))
    override fun toString(): String {
        return "RateLimit(limit=$limit, remaining=$remaining, resetDateTime=$resetDateTime)"
    }
}