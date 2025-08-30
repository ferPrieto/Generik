package com.generik.models.plain

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
