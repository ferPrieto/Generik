package com.fprieto.generik.extensions

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

/**
 * Kotlinx Serialization operations.
 * Provides utility functions for JSON serialization and deserialization using Kotlinx Serialization.
 */
object KotlinxSerializer {

    /**
     * Serializes an object to JSON string using a custom serializer.
     * @param value The object to serialize
     * @param serializer The custom serializer to use (defaults to the module's serializer)
     * @return JSON string representation of the object
     */
    inline fun <reified T : Any> toJson(
        value: T,
        serializer: KSerializer<T> = Json.serializersModule.serializer()
    ): String = Json.encodeToString(serializer, value)

    /**
     * Deserializes a JSON string to an object using a custom serializer.
     * @param json The JSON string to deserialize
     * @param serializer The custom serializer to use (defaults to the module's serializer)
     * @return The deserialized object of type T
     */
    inline fun <reified T : Any> fromJson(
        json: String,
        serializer: KSerializer<T> = Json.serializersModule.serializer()
    ): T = Json.decodeFromString(serializer, json)
} 