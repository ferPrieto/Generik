package com.fprieto.generik.extensions

import com.google.gson.Gson

/**
 * Extension functions for Gson serialization and deserialization.
 * These functions provide convenient ways to convert objects to/from JSON using Gson.
 */

/**
 * Converts any object to its JSON string representation using Gson.
 * @return JSON string representation of the object
 */
fun Any.toJson(): String = Gson().toJson(this)

/**
 * Converts a JSON string to an object of the specified type using Gson.
 * @param T The target type to deserialize to
 * @return The deserialized object of type T
 */
inline fun <reified T> String.fromJson(): T = Gson().fromJson(this, T::class.java) 