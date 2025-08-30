package com.generik.models.annotated

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val yearOfBirth: Int
)
