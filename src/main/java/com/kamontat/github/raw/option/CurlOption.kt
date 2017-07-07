package com.kamontat.github.raw.option

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 04/Jul/2017 - 3:39 PM
 */
enum class CurlOption(val query: () -> Unit) {
    TEST({
        print("Test")
    })
}