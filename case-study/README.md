# Generik Case Study

A comprehensive comparison of serialization approaches in Kotlin, showcasing the differences between traditional serialization libraries and the reflection-based GenericSerializer.

## The Problem with Traditional Serialization

Traditional serialization libraries often require:
- **Annotations**: Adding `@Serializable`, `@SerializedName`, etc., to data classes
- **Code Generation**: Compile-time processing to generate serialization logic
- **Library-Specific Models**: Creating separate model classes for each serialization library
- **Boilerplate Code**: Writing custom serializers for complex scenarios

Generik aims to solve these problems with a **reflection-based approach** that works with plain Kotlin data classes.

## Scenarios Overview

This case study demonstrates three different serialization approaches:

### 1. Scenario 1: Gson Serialization (`scenario1_gson`)
- **Approach**: Uses Google's Gson library
- **Models**: Plain data classes (no annotations)
- **Advantages**: Mature library, good performance, minimal setup
- **Trade-offs**: Limited customization without annotations

### 2. Scenario 2: Kotlinx Serialization (`scenario2_kotlinx`) 
- **Approach**: Uses Kotlin's official serialization library
- **Models**: Data classes with `@Serializable` annotations
- **Advantages**: Kotlin-native, type-safe, excellent performance
- **Trade-offs**: Requires annotations and compile-time setup

### 3. Scenario 3: Generik Serialization (`scenario3_generik`)
- **Approach**: Uses reflection-based GenericSerializer
- **Models**: Plain data classes (no annotations)
- **Advantages**: Zero boilerplate, works with any data class
- **Trade-offs**: Performance overhead, reflection limitations

## Shared Models

All scenarios work with the same conceptual data structure:

```kotlin
data class Person(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String?,
    val age: Int,
    val address: Address?,
    val company: Company?,
    val skills: List<String>,
    val salary: Double?,
    val isActive: Boolean
)

data class Address(
    val street: String,
    val city: String,
    val zipCode: String,
    val country: String
)

data class Company(
    val name: String,
    val address: Address,
    val industry: String,
    val founded: Int,
    val employees: Int,
    val isPublic: Boolean
)
```

## Running the Examples

### Individual Scenarios

Run each scenario to see serialization in action:

```bash
# Gson example
./gradlew :case-study:scenario1_gson:run

# Kotlinx Serialization example  
./gradlew :case-study:scenario2_kotlinx:run

# Generik example
./gradlew :case-study:scenario3_generik:run
```

### Benchmark Comparison

Run the performance benchmark to compare all three approaches:

```bash
./gradlew :case-study:benchmark:run
```

## Expected Results

### Sample Console Output

Each scenario will output similar information:

```
=== Scenario X: [Library] Serialization ===

📤 Serializing Person object...
JSON output:
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  ...
}

📥 Deserializing JSON back to Person object...
Deserialized object:
Person(id=1, firstName=John, lastName=Doe, ...)

✅ Serialization/Deserialization successful!
Objects match: true

JSON size: 245 bytes
```

### Benchmark Results Table

The benchmark produces a comparison table like:

```
| Library | Serialization Time (ms) | Deserialization Time (ms) | JSON Size (bytes) |
|---------|-------------------------|---------------------------|--------------------|
| Gson    | 89                      | 134                       | 245               |
| Kotlinx | 45                      | 67                        | 243               |
| Generik | ERROR: Reflection limitations prevent benchmarking   |
```

## Key Insights

### Performance Comparison
1. **Kotlinx Serialization**: Fastest due to compile-time optimization
2. **Gson**: Good performance with runtime reflection optimization
3. **Generik**: Slower due to pure reflection approach

### Development Experience
1. **Gson**: Minimal setup, works with plain classes
2. **Kotlinx**: Requires annotations but provides excellent tooling
3. **Generik**: Zero boilerplate but may face runtime limitations

### Production Readiness
1. **Gson**: Battle-tested, widely adopted
2. **Kotlinx**: Official Kotlin solution, highly recommended
3. **Generik**: Educational/experimental, demonstrates reflection concepts

## Limitations Demonstrated

The benchmark intentionally shows Generik's limitations:

- **Reflection Restrictions**: Modern JVM security can limit reflection access
- **Performance Overhead**: Runtime reflection is slower than compile-time generation
- **Complex Type Handling**: Nested objects and collections can be challenging
- **Error Handling**: Reflection failures can be hard to debug

## When to Use Each Approach

### Choose Gson when:
- Working with existing Java/Android projects
- Need simple, reliable JSON serialization
- Don't want to add Kotlin-specific dependencies

### Choose Kotlinx Serialization when:
- Building new Kotlin projects
- Want type-safe, compile-time verified serialization
- Need the best performance
- Prefer official Kotlin libraries

### Choose Generik when:
- Learning about reflection and serialization concepts
- Prototyping with plain data classes
- Need maximum flexibility over annotations

## Building and Testing

```bash
# Build all modules
./gradlew build

# Run all tests
./gradlew test

# Clean and rebuild
./gradlew clean build
```
