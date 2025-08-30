# Generik Library

A reflection-based generic serializer for Kotlin data classes.

## Overview

The Generik library provides a `GenericSerializer` class that enables automatic serialization and deserialization of Kotlin data classes without requiring annotations or code generation. It uses Kotlin reflection to dynamically analyze class structures and serialize/deserialize them to/from JSON.

## Usage

```kotlin
import com.generik.GenericSerializer
import kotlinx.serialization.json.Json

// Your data class (no annotations required)
data class Person(val name: String, val age: Int)

// Create a serializer
val serializer = GenericSerializer(Person::class)

// Serialize
val person = Person("John", 30)
val json = Json.encodeToString(serializer, person)
println(json) // {"name":"John","age":30}

// Deserialize
val deserializedPerson = Json.decodeFromString(serializer, json)
println(deserializedPerson) // Person(name=John, age=30)
```

## Features

- **No Annotations Required**: Works with plain Kotlin data classes
- **Reflection-Based**: Dynamically analyzes class structure at runtime
- **Nested Objects**: Supports nested data classes and complex object graphs
- **Collections**: Handles lists, maps, and other collections
- **Enums**: Automatic enum serialization/deserialization
- **Null Safety**: Handles nullable properties correctly

## Limitations

- **Performance**: Reflection-based approach has performance overhead
- **JVM Restrictions**: May face limitations in restricted environments due to reflection usage
- **Complex Types**: Limited support for very complex generic types

## Case Study

For comprehensive examples comparing Generik with other serialization approaches (Gson, Kotlinx Serialization), see the [case-study module](../case-study/README.md).

## Dependencies

- `kotlin-reflect`: For reflection capabilities
- `kotlinx-serialization-json`: For JSON serialization infrastructure
