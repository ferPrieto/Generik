package com.fprieto.generik.demo.serialization

import com.fprieto.generik.model.*
import com.fprieto.generik.extensions.fromJson
import com.fprieto.generik.extensions.toJson

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
