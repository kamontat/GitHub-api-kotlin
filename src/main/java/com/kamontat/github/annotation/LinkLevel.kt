package com.kamontat.github.annotation

enum class Level(val number: Int) {
    LEVEL_FIRST(1), LEVEL_2(2), LEVEL_3(3), LEVEL_4(4), LEVEL_5(5), LEVEL_6(6), LEVEL_LAST(7);

    fun compare(other: Level): Boolean {
        if (other.name == LEVEL_LAST.name) return true
        return Math.abs(this.number.minus(other.number)) == 1
    }
}

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 6:33 PM
 */
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class LinkLevel(val level: Level)