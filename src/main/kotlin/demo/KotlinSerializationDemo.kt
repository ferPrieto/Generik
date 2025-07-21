package demo

import demo.model.User
import demo.utils.KotlinxUtil
import serializer.GenericSerializer

class KotlinSerializationDemo {
    fun toJson(user: User): String {
        val serializer = GenericSerializer(User::class)
        val json = KotlinxUtil.toJson(user, serializer)

        println(json)
        return json
    }

    fun toModel(json: String): User {
        val serializer = GenericSerializer(User::class)
        val restoredUser: User = KotlinxUtil.fromJson(json, serializer)

        println(restoredUser)
        return restoredUser
    }
} 