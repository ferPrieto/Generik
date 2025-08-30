package com.generik.demo.serialization

import com.generik.extensions.KotlinxSerializer
import com.generik.models.plain.*
import com.generik.GenericSerializer

class ComplexKotlinxDemo {
    fun toJson(organization: Organization): String {
        val serializer = GenericSerializer(Organization::class)
        val json = KotlinxSerializer.toJson(organization, serializer)
        
        println(json)
        return json
    }

    fun toModel(json: String): Organization {
        val serializer = GenericSerializer(Organization::class)
        val restoredOrganization = KotlinxSerializer.fromJson(json, serializer)
       
        println(restoredOrganization)
        return restoredOrganization
    }
}
