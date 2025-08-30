package com.generik.models.plain

data class Company(
    val name: String,
    val address: Address,
    val industry: String,
    val founded: Int,
    val employees: List<Employee>,
    val departments: Map<String, List<String>>,
    val isPublic: Boolean
)
