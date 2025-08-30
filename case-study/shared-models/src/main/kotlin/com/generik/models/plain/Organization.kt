package com.generik.models.plain

data class Organization(
    val name: String,
    val type: OrganizationType,
    val headquarters: Address,
    val companies: List<Company>,
    val partnerships: Map<String, Partnership>,
    val globalMetrics: Map<String, Double>,
    val regions: List<String>,
    val certifications: List<String>,
    val founded: Int
)

enum class OrganizationType {
    CORPORATION,
    LLC,
    PARTNERSHIP,
    NON_PROFIT,
    GOVERNMENT
}

data class Partnership(
    val partnerName: String,
    val type: String,
    val since: String,
    val value: Double,
    val contacts: List<Contact>
)
