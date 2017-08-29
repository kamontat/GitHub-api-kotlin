package com.kamontat.github.model.gh

import com.kamontat.github.annotation.JsonKey

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 11/Jul/2017 - 5:17 PM
 */
data class UsersApiUrl(
        @JsonKey("html_url") val html_url: String?,
        @JsonKey("url") val api_url: String?,
        @JsonKey("followers_url") val followers_url: String?,
        @JsonKey("following_url") val following_url: String?,
        @JsonKey("gists_url") val gists_url: String?,
        @JsonKey("starred_url") val starred_url: String?,
        @JsonKey("subscriptions_url") val subscriptions_url: String?,
        @JsonKey("organizations_url") val organizations_url: String?,
        @JsonKey("repos_url") val repos_url: String?,
        @JsonKey("events_url") val events_url: String?,
        @JsonKey("received_events_url") val received_events_url: String?
) : ApiUrl(html_url,
        api_url,
        followers_url,
        following_url,
        gists_url,
        starred_url,
        subscriptions_url,
        organizations_url,
        repos_url,
        events_url,
        received_events_url) {
}