package com.generik.demo.serialization

import com.generik.models.annotated.User
import com.generik.extensions.fromJson
import com.generik.extensions.toJson

class GsonSerializationDemo {
    fun toJson(user: User): String {
        val json: String = user.toJson()

        println(json)
        return json
    }

    fun toModel(json: String): User {
        val restoredUser: User = json.fromJson()

        println(restoredUser)
        return restoredUser
    }
}
