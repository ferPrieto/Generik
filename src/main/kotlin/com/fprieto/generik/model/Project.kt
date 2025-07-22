package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val name: String,
    val description: String,
    val teamMembers: List<String>,
    val technologies: List<String>,
    val status: ProjectStatus,
    val startDate: String,
    val endDate: String?,
    val budget: Double,
    val tags: Map<String, String> = emptyMap()
)

@Serializable
enum class ProjectStatus {
    PLANNING,
    IN_PROGRESS,
    ON_HOLD,
    COMPLETED,
    CANCELLED
} 