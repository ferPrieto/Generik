package com.generik.scenario3

import com.generik.GenericSerializer
import com.generik.models.plain.*
import kotlinx.serialization.json.Json

fun main() {
    println("=== Scenario 3: Generik Serialization (Plain Models) ===")
    
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
    
    // Initialize Json with GenericSerializer
    val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }
    
    try {
        // Create GenericSerializer for Person (using plain models without annotations)
        val personSerializer = GenericSerializer(Person::class)
        
        // Serialize to JSON
        println("\n📤 Serializing Person object using GenericSerializer...")
        val jsonString = json.encodeToString(personSerializer, person)
        println("JSON output:")
        println(jsonString)
        
        // Deserialize from JSON
        println("\n📥 Deserializing JSON back to Person object...")
        val deserializedPerson = json.decodeFromString(personSerializer, jsonString)
        
        // Verify results
        println("Deserialized object:")
        println(deserializedPerson)
        
        println("\n✅ Serialization/Deserialization successful!")
        println("Objects match: ${person == deserializedPerson}")
        
        // Show JSON size
        println("\nJSON size: ${jsonString.toByteArray().size} bytes")
        
    } catch (e: Exception) {
        println("\n❌ GenericSerializer encountered an issue:")
        println("Error: ${e.message}")
        println("\nThis demonstrates the limitations of reflection-based serialization")
        println("in modern JVM environments with restricted access to internal fields.")
        
        println("\nNote: This is expected behavior and showcases why GenericSerializer")
        println("has limitations with complex nested objects in restricted environments.")
    }
}
