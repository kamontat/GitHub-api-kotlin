package com.kamontat.github.model.gh

import com.kamontat.github.annotation.JsonKey

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 10:10 PM
 */
@JsonKey("resources")
data class ResourceRateLimit(
        @JsonKey("core") val core: RateLimit,
        @JsonKey("search") val search: RateLimit,
        @JsonKey("graphql") val graphQL: RateLimit
) : GObject() {
}