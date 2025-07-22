package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: String,
    val name: String,
    val description: String,
    val members: List<TeamMember>,  
    val projects: List<Project>,
    val roles: Map<String, List<Role>>,
    val subTeams: List<Team> = emptyList(),
    val collaborations: List<TeamCollaboration> = emptyList(),
    val resources: Map<String, TeamResource> = emptyMap()
)

@Serializable
data class TeamMember(
    val employee: Employee,
    val joinedDate: String,
    val role: Role,
    val responsibilities: List<Responsibility>,
    val performance: Map<String, PerformanceMetric> = emptyMap()
)

@Serializable
data class Responsibility(
    val id: String,
    val title: String,
    val description: String,
    val priority: Priority,
    val assignedProjects: List<Project> = emptyList(),
    val requiredSkills: List<Skill> = emptyList()
)

@Serializable
data class Skill(
    val name: String,
    val category: String,
    val level: SkillLevel,
    val certifications: List<Certification> = emptyList()
)

@Serializable
data class Certification(
    val name: String,
    val issuer: String,
    val issueDate: String,
    val expiryDate: String?,
    val credentialId: String
)

@Serializable
data class PerformanceMetric(
    val metric: String,
    val value: Double,
    val period: String,
    val benchmarks: List<Benchmark> = emptyList()
)

@Serializable
data class Benchmark(
    val name: String,
    val value: Double,
    val source: String
)

@Serializable
data class TeamCollaboration(
    val partnerTeam: Team,
    val type: CollaborationType,
    val startDate: String,
    val endDate: String?,
    val sharedProjects: List<Project> = emptyList(),
    val agreements: Map<String, Agreement> = emptyMap()
)

@Serializable
data class Agreement(
    val title: String,
    val terms: List<String>,
    val signedBy: List<String>,
    val effectiveDate: String
)

@Serializable
data class TeamResource(
    val name: String,
    val type: ResourceType,
    val allocation: ResourceAllocation,
    val dependencies: List<TeamResource> = emptyList()
)

@Serializable
data class ResourceAllocation(
    val percentage: Double,
    val startDate: String,
    val endDate: String?,
    val cost: Double
)

@Serializable
enum class Priority {
    LOW, MEDIUM, HIGH, CRITICAL
}

@Serializable
enum class SkillLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
}

@Serializable
enum class CollaborationType {
    PARTNERSHIP, JOINT_VENTURE, SHARED_RESOURCES, KNOWLEDGE_EXCHANGE
}

@Serializable
enum class ResourceType {
    HUMAN, TECHNICAL, FINANCIAL, INFRASTRUCTURE
} 
