package com.kamontat.github.model.gh

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonBase
import com.beust.klaxon.JsonObject
import com.kamontat.github.annotation.JsonKey
import com.kamontat.github.exception.constants.ErrorCode
import com.kamontat.github.exception.instants.GithubExceptionInstant
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 10/Jul/2017 - 2:52 PM
 */
object GHObjectBuilder {
    inline fun <reified T : GObject> build(tClass: KClass<T>, json: JsonBase): T? {
        if (json::class != JsonObject::class) throw GithubExceptionInstant.BuilderError.get(ErrorCode.WRONG_PARAMETER)
        json as JsonObject

        if (tClass.primaryConstructor == null) return null
        val map: HashMap<KParameter, Any?> = HashMap<KParameter, Any?>()

        tClass.primaryConstructor!!.parameters.forEach {
            params ->
            val keyAnnotation: JsonKey? = params.annotations.filter {
                annotation ->
                return@filter annotation.annotationClass == JsonKey::class
            }.singleOrNull() as JsonKey
            val value = json[keyAnnotation?.key]
            map.put(params, value)
        }
        return tClass.primaryConstructor!!.callBy(map)
    }

    inline fun <reified T : GObject> builds(tClass: KClass<T>, json: JsonBase): Array<T?> {
        if (json::class != JsonArray::class) throw GithubExceptionInstant.BuilderError.get(ErrorCode.WRONG_PARAMETER)
        try {
            if ((json as JsonArray<*>).first()!!::class != JsonObject::class) throw GithubExceptionInstant.BuilderError.get(ErrorCode.WRONG_PARAMETER)
            else json as JsonArray<JsonObject>
        } catch (e: NoSuchElementException) {
            throw GithubExceptionInstant.BuilderError.get(ErrorCode.WRONG_PARAMETER, e)
        }

        val arr = arrayOfNulls<T>(json.size)
        json.forEachIndexed {
            i, json ->
            arr[i] = build(tClass, json)
        }
        return arr
    }
}