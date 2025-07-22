package com.fprieto.generik.demo.serialization

import com.fprieto.generik.model.*
import com.fprieto.generik.extensions.KotlinxSerializer
import com.fprieto.generik.serializer.GenericSerializer

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
