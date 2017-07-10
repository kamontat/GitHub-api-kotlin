package com.kamontat.github.annotation

import kotlin.reflect.KClass

enum class Level(val number: Int) {
    LEVEL_FIRST(1), LEVEL_2(2), LEVEL_3(3), LEVEL_4(4), LEVEL_5(5), LEVEL_6(6), LEVEL_LAST(7);

    fun compare(other: Level): Boolean = other == LEVEL_LAST || other.number.minus(this.number) == 1
}

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 6:33 PM
 */
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class ELevel(val level: Level)

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 06/Jul/2017 - 6:33 PM
 */
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class Require(val nextClass: KClass<*>)

@Target(AnnotationTarget.FUNCTION)
annotation class Optional(val nextClass: KClass<*>)

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class JsonKey(val key: String)