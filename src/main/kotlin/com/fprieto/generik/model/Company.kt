package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val name: String,
    val address: Address,
    val employees: List<Employee>,
    val departments: Map<String, List<String>>,
    val founded: Int,
    val isPublic: Boolean
) 