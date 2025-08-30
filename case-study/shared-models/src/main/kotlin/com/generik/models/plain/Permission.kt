package com.generik.models.plain

data class Permission(
    val id: String,
    val name: String,
    val resource: String,
    val actions: List<String>,
    val conditions: Map<String, String> = emptyMap()
)

enum class PermissionLevel {
    READ,
    WRITE,
    ADMIN,
    OWNER
}
