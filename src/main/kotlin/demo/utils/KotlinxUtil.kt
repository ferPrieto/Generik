package demo.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

object KotlinxUtil {

    inline fun <reified T : Any> toJson(
        value: T,
        serializer: KSerializer<T> = Json.serializersModule.serializer()
    ): String = Json.encodeToString(serializer, value)


    inline fun <reified T : Any> fromJson(
        json: String,
        serializer: KSerializer<T> = Json.serializersModule.serializer()
    ): T = Json.decodeFromString(serializer, json)
} 