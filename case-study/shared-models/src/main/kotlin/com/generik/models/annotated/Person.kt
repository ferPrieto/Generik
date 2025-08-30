package com.generik.models.annotated

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String?,
    val age: Int,
    val address: Address?,
    val company: Company?,
    val skills: List<String>,
    val salary: Double?,
    val isActive: Boolean
)
