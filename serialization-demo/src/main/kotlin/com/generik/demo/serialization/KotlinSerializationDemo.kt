package com.generik.demo.serialization

import com.generik.models.plain.User
import com.generik.extensions.KotlinxSerializer
import com.generik.GenericSerializer

class KotlinSerializationDemo {
    fun toJson(user: User): String {
        val serializer = GenericSerializer(User::class)
        val json = KotlinxSerializer.toJson(user, serializer)

        println(json)
        return json
    }

    fun toModel(json: String): User {
        val serializer = GenericSerializer(User::class)
        val restoredUser: User = KotlinxSerializer.fromJson(json, serializer)

        println(restoredUser)
        return restoredUser
    }
}
