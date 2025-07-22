package com.fprieto.generik.demo.serialization

import com.fprieto.generik.extensions.KotlinxSerializer
import com.fprieto.generik.model.*
import com.fprieto.generik.serializer.GenericSerializer

class ComplexKotlinxDemo {
    fun toJson(organization: Organization): String {
        val serializer = GenericSerializer(Organization::class)
        val json = KotlinxSerializer.toJson(organization, serializer)
        
        println(json)
        return json
    }

    fun toModel(json: String): Organization {
        val serializer = GenericSerializer(Organization::class)
        val restoredUOrganization = KotlinxSerializer.fromJson(json, serializer)
       
        println(restoredUOrganization)
        return restoredUOrganization
    }
} 
