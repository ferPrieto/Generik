package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: String,
    val personalInfo: User,
    val contact: Contact,
    val address: Address,
    val position: String,
    val department: String,
    val salary: Double,
    val skills: List<String>,
    val projects: List<Project>,
    val metadata: Map<String, String> = emptyMap()
) 