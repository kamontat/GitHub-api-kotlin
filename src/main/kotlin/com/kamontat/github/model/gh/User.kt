package com.kamontat.github.model.gh

import com.kamontat.github.annotation.JsonKey

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 2:50 PM
 */
data class User(
        @JsonKey("login") val login: String,
        @JsonKey("id") val id: Long,
        @JsonKey("avatar_url") val avatar_url: String?,
        @JsonKey("gravatar_id") val gravatar_id: String?,
        @JsonKey("type") val type: String,
        @JsonKey("site_admin") val site_admin: Boolean,
        @JsonKey("name") val name: String?,
        @JsonKey("company") val company: String?,
        @JsonKey("blog") val blog: String?,
        @JsonKey("location") val location: String?,
        @JsonKey("email") val email: String?,
        @JsonKey("hireable") val hireable: Boolean,
        @JsonKey("bio") val bio: String?,
        @JsonKey("public_repos") val public_repos: Int,
        @JsonKey("public_gists") val public_gists: Int,
        @JsonKey("followers") val followers: Int?,
        @JsonKey("following") val following: Int?,
        @JsonKey("created_at") val created_at: String,
        @JsonKey("updated_at") val updated_at: String,
        @JsonKey("private_gists") val private_gists: Int?,
        @JsonKey("total_private_repos") val total_private_repos: Int?,
        @JsonKey("owned_private_repos") val owned_private_repos: Int?,
        @JsonKey("disk_usage") val disk_usage: Int?,
        @JsonKey("collaborators") val collaborators: Int?,
        @JsonKey("two_factor_authentication") val two_factor_authentication: Boolean?,
        @JsonKey("plan") val plan: Plan?,
        val api_url: ApiUrl?
) : GObject() {

}