package demo

import demo.model.User
import demo.utils.fromJson
import demo.utils.toJson

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