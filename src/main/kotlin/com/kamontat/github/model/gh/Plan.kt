package com.kamontat.github.model.gh

import com.kamontat.github.annotation.JsonKey

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 8:29 PM
 */
data class Plan(
        @JsonKey("name") val name: String,
        @JsonKey("space") val space: Long,
        @JsonKey("collaborators") val collaborators: Int,
        @JsonKey("private_repos") val private_repos: Int
) : GObject() {

}