package com.fprieto.generik.demo.serialization

import com.fprieto.generik.model.User
import com.fprieto.generik.extensions.fromJson
import com.fprieto.generik.extensions.toJson

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