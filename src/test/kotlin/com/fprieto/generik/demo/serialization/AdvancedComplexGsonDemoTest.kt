package com.fprieto.generik.demo.serialization

import com.fprieto.generik.model.*
import com.fprieto.generik.demo.serialization.AdvancedComplexGsonDemo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AdvancedComplexGsonDemoTest {

    private val sut = AdvancedComplexGsonDemo()

    @Test
    fun `Given Enterprise when toJson then expected result`() {
        // Given
        val expected = "{\"id\":\"ent-test\",\"name\":\"Test Enterprise\",\"headquarters\":{\"street\":\"123 Test St\",\"city\":\"Test City\",\"zipCode\":\"12345\",\"country\":\"USA\"},\"organizations\":[],\"teams\":{},\"globalRoles\":[],\"partnerships\":{},\"acquisitions\":[],\"boardMembers\":[],\"financials\":{},\"compliance\":{\"regulations\":[],\"audits\":[],\"certifications\":[],\"policies\":{}},\"founded\":2020,\"stockExchanges\":[]}"
        val enterprise = Enterprise(
            id = "ent-test",
            name = "Test Enterprise",
            headquarters = Address("123 Test St", "Test City", "12345", "USA"),
            organizations = emptyList(),
            teams = emptyMap(),
            globalRoles = emptyList(),
            partnerships = emptyMap(),
            acquisitions = emptyList(),
            boardMembers = emptyList(),
            financials = emptyMap(),
            compliance = ComplianceFramework(
                regulations = emptyList(),
                audits = emptyList(),
                certifications = emptyList(),
                policies = emptyMap()
            ),
            founded = 2020,
            stockExchanges = emptyList()
        )

        // When
        val actual = sut.toJson(enterprise)

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `Given Json when toModel then expected result`() {
        // Given
        val json = "{\"id\":\"ent-test\",\"name\":\"Test Enterprise\",\"headquarters\":{\"street\":\"123 Test St\",\"city\":\"Test City\",\"zipCode\":\"12345\",\"country\":\"USA\"},\"organizations\":[],\"teams\":{},\"globalRoles\":[],\"partnerships\":{},\"acquisitions\":[],\"boardMembers\":[],\"financials\":{},\"compliance\":{\"regulations\":[],\"audits\":[],\"certifications\":[],\"policies\":{}},\"founded\":2020,\"stockExchanges\":[]}"
        val expected = Enterprise(
            id = "ent-test",
            name = "Test Enterprise",
            headquarters = Address("123 Test St", "Test City", "12345", "USA"),
            organizations = emptyList(),
            teams = emptyMap(),
            globalRoles = emptyList(),
            partnerships = emptyMap(),
            acquisitions = emptyList(),
            boardMembers = emptyList(),
            financials = emptyMap(),
            compliance = ComplianceFramework(
                regulations = emptyList(),
                audits = emptyList(),
                certifications = emptyList(),
                policies = emptyMap()
            ),
            founded = 2020,
            stockExchanges = emptyList()
        )

        // When
        val actual = sut.toModel(json)

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `Given Enterprise when toJson and toModel then expected restored model`() {
        // Given
        val address = Address("Complex HQ", "Enterprise City", "54321", "USA")
        val user = User("Enterprise CEO", 1975)
        val contact = Contact("ceo@enterprise.com", "+1-555-CEO1", mapOf("linkedin" to "ceo-profile"))
        
        val employee = Employee(
            id = "emp-001",
            personalInfo = user,
            contact = contact,
            address = address,
            position = "CEO",
            department = "Executive",
            salary = 500000.0,
            skills = listOf("Leadership", "Strategy", "Vision"),
            projects = emptyList(),
            metadata = mapOf("hire_date" to "2020-01-01", "performance" to "excellent")
        )
        
        val company = Company(
            name = "Enterprise Corp",
            address = address,
            employees = listOf(employee),
            departments = mapOf("Executive" to listOf("emp-001"), "Engineering" to listOf()),
            founded = 2020,
            isPublic = true
        )
        
        val organization = Organization(
            name = "Enterprise Organization",
            type = OrganizationType.CORPORATION,
            headquarters = address,
            companies = listOf(company),
            partnerships = emptyMap(),
            globalMetrics = mapOf("revenue" to 1000000.0, "employees" to 1.0),
            regions = listOf("North America", "Europe"),
            certifications = listOf("ISO 9001", "SOC 2"),
            founded = 2020
        )
        
        val complianceFramework = ComplianceFramework(
            regulations = emptyList(),
            audits = emptyList(),
            certifications = emptyList(),
            policies = emptyMap()
        )
        
        val originalEnterprise = Enterprise(
            id = "ent-complex-001",
            name = "Complex Enterprise Holdings",
            headquarters = address,
            organizations = listOf(organization),
            teams = emptyMap(),
            globalRoles = emptyList(),
            partnerships = emptyMap(),
            acquisitions = emptyList(),
            boardMembers = emptyList(),
            financials = emptyMap(),
            compliance = complianceFramework,
            founded = 2020,
            stockExchanges = emptyList()
        )

        // When
        val json = sut.toJson(originalEnterprise)
        val restoredEnterprise = sut.toModel(json)

        // Then
        assertNotNull(json)
        assertNotNull(restoredEnterprise)
        assertEquals(originalEnterprise.id, restoredEnterprise.id)
        assertEquals(originalEnterprise.name, restoredEnterprise.name)
        assertEquals(originalEnterprise.founded, restoredEnterprise.founded)
        assertEquals(originalEnterprise.organizations.size, restoredEnterprise.organizations.size)
        assertEquals(originalEnterprise.organizations[0].name, restoredEnterprise.organizations[0].name)
        assertEquals(originalEnterprise.organizations[0].companies.size, restoredEnterprise.organizations[0].companies.size)
        assertEquals(originalEnterprise.organizations[0].companies[0].employees.size, restoredEnterprise.organizations[0].companies[0].employees.size)
    }
} 
