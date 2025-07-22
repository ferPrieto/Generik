package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class Permission(
    val id: String,
    val name: String,
    val resource: String,
    val actions: List<String>,
    val conditions: Map<String, String> = emptyMap()
)

@Serializable
enum class PermissionLevel {
    READ,
    WRITE,
    ADMIN,
    OWNER
} 