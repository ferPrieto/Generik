package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val street: String,
    val city: String,
    val zipCode: String,
    val country: String
) 