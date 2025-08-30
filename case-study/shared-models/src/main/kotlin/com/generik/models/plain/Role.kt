package com.generik.models.plain

data class Role(
    val id: String,
    val name: String,
    val description: String,
    val permissions: List<Permission>, 
    val inheritedRoles: List<Role> = emptyList(),
    val level: PermissionLevel,
    val metadata: Map<String, RoleMetadata> = emptyMap()  
)

data class RoleMetadata(
    val createdBy: String,
    val createdAt: String,
    val lastModified: String,
    val tags: List<String> = emptyList()
)
