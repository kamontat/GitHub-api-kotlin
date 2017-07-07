package com.kamontat.github

import com.kamontat.github.model.link.GithubLink

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 6:09 PM
 */
fun main(args: Array<String>) {
    println(GithubLink.create().REPOS().get());
}