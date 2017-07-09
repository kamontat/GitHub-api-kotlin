package com.kamontat.github.model.auth

import com.beust.klaxon.JSON
import com.beust.klaxon.JsonObject
import com.beust.klaxon.string
import com.kamontat.github.extension.fromStringBuilder
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

/**
 * save and load github config from .github folder
 * **all data save as json format**
 *
 * @author kamontat
 * @version 1.0
 * @since Sun 09/Jul/2017 - 9:30 PM
 */
class FileLoader() {
    private val file: File = File(FILE_NAME)

    private var json: JsonObject = com.beust.klaxon.json {
        obj(KEY.OAUTH.title to "", KEY.CREATE_AT.title to "", KEY.UPDATE_AT.title to "")
    }

    /**
     * key is on [Constants.KEY]
     */
    fun getByKey(key: KEY): String {
        return json.string(key.title) ?: ""
    }

    fun newOAUTH(oauth: String) {
        setByKey(KEY.OAUTH, oauth)
    }

    /**
     * key is on [Constants.KEY]
     */
    private fun setByKey(key: KEY, value: String) {
        json.put(key.title, value)
    }

    companion object Constants {
        val FILE_NAME: String = "${System.getProperty("user.home")}${File.separator}.github.json"

        enum class KEY(val title: String) {
            OAUTH("oauth"), CREATE_AT("create_at"), UPDATE_AT("update_at")
        }
    }

    fun load(): Boolean {
        if (file.exists() && file.length() != 0L) {
            json = JSON().fromStringBuilder(Files.readAllLines(Paths.get(FILE_NAME)).joinTo(StringBuilder(), ""))
            return false
        } else {
            val result = file.createNewFile()
            setByKey(KEY.CREATE_AT, getDateAndTime())
            setByKey(KEY.UPDATE_AT, getDateAndTime())
            write()
            return result
        }
    }

    fun save(): Boolean {
        if (getByKey(KEY.CREATE_AT) == "") setByKey(KEY.CREATE_AT, getDateAndTime())
        setByKey(KEY.UPDATE_AT, getDateAndTime())
        write()
        return true
    }

    private fun getDateAndTime(): String {
        return SimpleDateFormat("EEE dd/MMM/yyyy-HH:mm:ss:SS (zzzZ)").format(Date.from(Instant.now()))
    }

    private fun write() {
        file.writeText(json.toJsonString(true))
    }
}