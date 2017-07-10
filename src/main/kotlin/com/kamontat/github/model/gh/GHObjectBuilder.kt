package com.kamontat.github.model.gh

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonBase
import com.beust.klaxon.JsonObject
import com.beust.klaxon.obj
import com.kamontat.github.annotation.JsonKey
import com.kamontat.github.exception.constants.ErrorCode
import com.kamontat.github.exception.instants.GithubExceptionInstant
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

/**
 * @author kamontat
 * @version 1.1
 * @since Mon 10/Jul/2017 - 2:52 PM
 */
object GHObjectBuilder {
    fun <T : GObject> build(tClass: KClass<T>, json: JsonBase): T? {
        if (json::class != JsonObject::class) throw GithubExceptionInstant.BuilderError.get(ErrorCode.WRONG_PARAMETER)
        var jsonObject = json as JsonObject

        if (tClass.primaryConstructor == null) return null
        val classAnnotation: JsonKey? = tClass.findAnnotation<JsonKey>()
        if (classAnnotation != null) jsonObject = json.obj(classAnnotation.key) ?: throw GithubExceptionInstant.BuilderError.get(ErrorCode.WRONG_JSON_KEY)
        val map: HashMap<KParameter, Any?> = HashMap<KParameter, Any?>()

        tClass.primaryConstructor!!.parameters.forEach {
            params ->
            val keyAnnotation: JsonKey? = params.annotations.filter {
                annotation ->
                return@filter annotation.annotationClass == JsonKey::class
            }.singleOrNull() as JsonKey
            val value = jsonObject[keyAnnotation?.key]
            if (value != null && value::class == JsonObject::class)
                map.put(params, build(params.type.jvmErasure as KClass<T>, value as JsonObject))
            else map.put(params, value)

            return@forEach
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