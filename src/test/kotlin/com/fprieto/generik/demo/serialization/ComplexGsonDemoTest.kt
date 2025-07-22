package com.fprieto.generik.demo.serialization

import com.fprieto.generik.model.*
import com.fprieto.generik.demo.serialization.ComplexGsonDemo
import kotlin.test.Test
import kotlin.test.assertEquals

class ComplexGsonDemoTest {

    private val sut = ComplexGsonDemo()

    @Test
    fun `Given Organization when toJson then expected result`() {
        // Given
        val expected = "{\"name\":\"Test Organization\",\"type\":\"CORPORATION\",\"headquarters\":{\"street\":\"123 Main St\",\"city\":\"San Francisco\",\"zipCode\":\"94105\",\"country\":\"USA\"},\"companies\":[],\"partnerships\":{},\"globalMetrics\":{},\"regions\":[],\"certifications\":[],\"founded\":2020}"
        val organization = Organization(
            name = "Test Organization",
            type = OrganizationType.CORPORATION,
            headquarters = Address("123 Main St", "San Francisco", "94105", "USA"),
            companies = emptyList(),
            partnerships = emptyMap(),
            globalMetrics = emptyMap(),
            regions = emptyList(),
            certifications = emptyList(),
            founded = 2020
        )

        // When
        val actual = sut.toJson(organization)

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `Given Json when toModel then expected result`() {
        // Given
        val json = "{\"name\":\"Test Organization\",\"type\":\"CORPORATION\",\"headquarters\":{\"street\":\"123 Main St\",\"city\":\"San Francisco\",\"zipCode\":\"94105\",\"country\":\"USA\"},\"companies\":[],\"partnerships\":{},\"globalMetrics\":{},\"regions\":[],\"certifications\":[],\"founded\":2020}"
        val expected = Organization(
            name = "Test Organization",
            type = OrganizationType.CORPORATION,
            headquarters = Address("123 Main St", "San Francisco", "94105", "USA"),
            companies = emptyList(),
            partnerships = emptyMap(),
            globalMetrics = emptyMap(),
            regions = emptyList(),
            certifications = emptyList(),
            founded = 2020
        )

        // When
        val actual = sut.toModel(json)

        // Then
        assertEquals(expected, actual)
    }
} 