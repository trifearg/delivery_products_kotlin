package ru.app.fastestdelivery.data.errors

import com.squareup.moshi.Moshi
import java.lang.Exception
import javax.inject.Inject

class ErrorsConverter @Inject constructor() {

    private val moshi = Moshi.Builder().build()

    fun <T> convert(jsonString: String, type: Class<T>): T? {
        return try {
            moshi.adapter(type).fromJson(jsonString)
        } catch (e: Exception) {
            null
        }
    }

}