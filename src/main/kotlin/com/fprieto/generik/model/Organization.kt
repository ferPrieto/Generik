package com.fprieto.generik.model

import kotlinx.serialization.Serializable

@Serializable
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

@Serializable
enum class OrganizationType {
    CORPORATION,
    LLC,
    PARTNERSHIP,
    NON_PROFIT,
    GOVERNMENT
}

@Serializable
data class Partnership(
    val partnerName: String,
    val type: String,
    val since: String,
    val value: Double,
    val contacts: List<Contact>
) 