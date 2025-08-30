package com.generik.scenario2

import com.generik.models.annotated.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    println("=== Scenario 2: Kotlinx Serialization ===")
    
    // Create test data
    val address = Address(
        street = "123 Main St",
        city = "San Francisco",
        zipCode = "94102",
        country = "USA"
    )
    
    val company = Company(
        name = "Tech Corp",
        address = address,
        industry = "Technology",
        founded = 2015,
        employees = emptyList(),
        departments = mapOf("Engineering" to listOf("emp1", "emp2"), "Marketing" to listOf("emp3")),
        isPublic = true
    )
    
    val person = Person(
        id = 1L,
        firstName = "John",
        lastName = "Doe",
        email = "john.doe@example.com",
        age = 30,
        address = address,
        company = company,
        skills = listOf("Kotlin", "Java", "Spring", "Microservices"),
        salary = 95000.50,
        isActive = true
    )
    
    // Initialize Json with pretty printing
    val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }
    
    // Serialize to JSON
    println("\n📤 Serializing Person object...")
    val jsonString = json.encodeToString(person)
    println("JSON output:")
    println(jsonString)
    
    // Deserialize from JSON
    println("\n📥 Deserializing JSON back to Person object...")
    val deserializedPerson = json.decodeFromString<Person>(jsonString)
    
    // Verify results
    println("Deserialized object:")
    println(deserializedPerson)
    
    println("\n✅ Serialization/Deserialization successful!")
    println("Objects match: ${person == deserializedPerson}")
    
    // Show JSON size
    println("\nJSON size: ${jsonString.toByteArray().size} bytes")
}
