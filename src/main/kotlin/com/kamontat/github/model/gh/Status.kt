package com.kamontat.github.model.gh

import com.kamontat.github.annotation.JsonKey
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 13/Jul/2017 - 10:58 AM
 */
data class Status(
        @JsonKey("status") val status: String,
        @JsonKey("body") val body: String,
        @JsonKey("created_on") val created_on: Date
) : GObject() {

}