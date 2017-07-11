package com.kamontat.github.util

import java.util.logging.Logger

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 4:13 PM
 */
object Log {
    fun create(debug: Boolean = false): Logger? {
        return when (debug) {
            true -> Logger.getGlobal()
            else -> null
        }
    }
}