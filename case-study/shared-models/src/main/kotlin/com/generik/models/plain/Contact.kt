package com.generik.models.plain

data class Contact(
    val email: String,
    val phone: String,
    val socialMedia: Map<String, String> = emptyMap()
)
