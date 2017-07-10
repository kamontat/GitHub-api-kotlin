package com.kamontat.github.util

import java.util.logging.LogManager
import java.util.logging.Logger

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 4:13 PM
 */
class Log(private val debug: Boolean) {
    fun get(): Logger? {
        if (debug) return Logger.getGlobal()
        else return null
    }
}