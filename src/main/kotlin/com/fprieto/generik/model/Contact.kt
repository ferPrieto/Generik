package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val email: String,
    val phone: String,
    val socialMedia: Map<String, String> = emptyMap()
) 