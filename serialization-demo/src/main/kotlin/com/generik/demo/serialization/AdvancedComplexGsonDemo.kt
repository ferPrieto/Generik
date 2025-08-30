package com.generik.demo.serialization

import com.generik.models.annotated.*
import com.generik.extensions.fromJson
import com.generik.extensions.toJson

class AdvancedComplexGsonDemo {

    fun toJson(enterprise: Enterprise): String {
        val json: String = enterprise.toJson()

        println(json)
        return json
    }

    fun toModel(json: String): Enterprise {
        val restoredEnterprise: Enterprise = json.fromJson()

        println(restoredEnterprise)
        return restoredEnterprise
    }
}
