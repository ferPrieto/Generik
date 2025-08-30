package com.generik.demo.serialization

import com.generik.models.annotated.*
import com.generik.extensions.fromJson
import com.generik.extensions.toJson

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
