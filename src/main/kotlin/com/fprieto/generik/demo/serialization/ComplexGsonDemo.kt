package com.fprieto.generik.demo.serialization

import com.fprieto.generik.model.*
import com.fprieto.generik.extensions.fromJson
import com.fprieto.generik.extensions.toJson

class ComplexGsonDemo {
      fun toJson(organization: Organization): String {
        val json: String = organization.toJson()

        println(json)
        return json
    }

    fun toModel(json: String): Organization {
        val restoredOrganization: Organization = json.fromJson()

        println(restoredOrganization)
        return restoredOrganization
    }
} 
