package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val yearOfBirth: Int
) 