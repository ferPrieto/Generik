package com.generik.demo.serialization

import com.generik.models.plain.*
import com.generik.extensions.KotlinxSerializer
import com.generik.GenericSerializer

class AdvancedComplexKotlinxDemo {
    
    fun toJson(enterprise: Enterprise): String {
        val serializer = GenericSerializer(Enterprise::class)
        return KotlinxSerializer.toJson(enterprise, serializer)
    }
    
    fun toModel(json: String): Enterprise {
        val serializer = GenericSerializer(Enterprise::class)
        return KotlinxSerializer.fromJson(json, serializer)
    }
}
