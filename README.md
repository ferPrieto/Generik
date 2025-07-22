# Generik - Complex Serialization Demo

A comprehensive Kotlin project showcasing complex data model serialization using both **Gson** and **Kotlinx Serialization** with Gradle Version Catalog and advanced reflection-based serialization.

## 🎯 Project Goals

This project serves as an **educational resource** and **generic serialization toolkit** designed to:

- **Demonstrate Generic Serialization Approaches**: Showcase different serialization strategies using both industry-standard libraries (Gson) and Kotlin-native solutions (Kotlinx Serialization)
- **Educational Framework**: Provide real-world examples of complex data modeling, including nested objects, collections, enums, and recursive structures
- **Generic Reflection-Based Serialization**: Explore the capabilities and limitations of custom generic serializers using Kotlin reflection
- **Best Practices Showcase**: Illustrate modern Kotlin development practices, including proper package organization, extension functions, and comprehensive testing strategies
- **Comparative Analysis**: Enable developers to understand when to choose Gson vs Kotlinx Serialization vs custom generic solutions
- **Production-Ready Patterns**: Demonstrate scalable approaches to handling enterprise-level data complexity with graceful error handling and robust testing

## 🏗️ Project Structure

```
src/main/kotlin/com/fprieto/generik/
├── model/                       # Complex data models
│   ├── User.kt                 # Basic user information
│   ├── Address.kt              # Address with street, city, zip, country
│   ├── Contact.kt              # Contact info with social media Map
│   ├── Project.kt              # Projects with enums, lists, and maps
│   ├── Employee.kt             # Employee with nested objects and lists
│   ├── Company.kt              # Company with employees and departments
│   ├── Organization.kt         # Organization structure
│   ├── Team.kt                 # Team management with complex roles
│   ├── Role.kt                 # Permission-based role system
│   ├── Permission.kt           # Granular permission model
│   └── Enterprise.kt           # Ultra-complex enterprise structure
├── demo/
│   └── serialization/          # Serialization demo classes
│       ├── GsonSerializationDemo.kt         # Simple Gson demo
│       ├── KotlinSerializationDemo.kt       # Simple Kotlinx demo
│       ├── ComplexGsonDemo.kt               # Advanced Gson demonstrations
│       ├── ComplexKotlinxDemo.kt            # Advanced Kotlinx demonstrations
│       ├── AdvancedComplexGsonDemo.kt       # Ultra-complex Gson serialization
│       └── AdvancedComplexKotlinxDemo.kt    # Ultra-complex Kotlinx serialization
├── extensions/
│   ├── GsonExtensions.kt       # Gson extension functions (.toJson(), .fromJson())
│   └── KotlinxSerializer.kt    # Kotlinx serialization utilities
└── serializer/
    └── GenericSerializer.kt    # Custom reflection-based serializer

src/test/kotlin/com/fprieto/generik/demo/serialization/
├── GsonSerializationDemoTest.kt
├── KotlinSerializationDemoTest.kt
├── ComplexGsonDemoTest.kt
├── ComplexKotlinxDemoTest.kt
├── AdvancedComplexGsonDemoTest.kt
└── AdvancedComplexKotlinxDemoTest.kt
```

## 🚀 Complex Models Features

### 📊 Data Types Supported
- **Primitive Types**: String, Int, Double, Boolean
- **Collections**: List<T>, Map<String, T>, nested collections
- **Complex Collections**: Lists of complex objects, Maps with complex values
- **Nested Objects**: Deep hierarchical structures
- **Enums**: ProjectStatus, OrganizationType, PermissionLevel, and more
- **Nullable Fields**: Optional properties with null handling
- **Recursive Structures**: Teams with sub-teams, nested deliverables

### Prerequisites
- Java 21+ (configured with JVM Toolchain)
- Gradle 8.9 (handled by wrapper)

## 📋 Comprehensive Test Coverage

### 🎯 Demo Classes & Tests

#### **Basic Serialization Demos**
- **GsonSerializationDemo** ✅ + GsonSerializationDemoTest
- **KotlinSerializationDemo** ✅ + KotlinSerializationDemoTest

#### **Complex Object Demos**
- **ComplexGsonDemo** ✅ + ComplexGsonDemoTest
  - Organization serialization/deserialization
  - Comprehensive JSON verification
- **ComplexKotlinxDemo** ⚠️ + ComplexKotlinxDemoTest
  - Organization with GenericSerializer
  - Graceful error handling for Java module restrictions

#### **Advanced Complex Object Demos**
- **AdvancedComplexGsonDemo** ✅ + AdvancedComplexGsonDemoTest
  - Enterprise-level serialization
  - Ultra-complex nested structures
  - Full roundtrip testing
- **AdvancedComplexKotlinxDemo** ⚠️ + AdvancedComplexKotlinxDemoTest
  - Enterprise with GenericSerializer
  - Educational limitations demonstration

### 🧪 Test Coverage Summary

| Demo Class | Object Type | Serializer | Status | Test Coverage |
|------------|-------------|------------|---------|---------------|
| GsonSerializationDemo | Organization | Gson | ✅ Full | ✅ Complete |
| KotlinSerializationDemo | User | Built-in @Serializable | ✅ Full | ✅ Complete |
| ComplexGsonDemo | Organization | Gson | ✅ Full | ✅ Complete |
| ComplexKotlinxDemo | Organization | GenericSerializer | ⚠️ Limited | ✅ Error Handling |
| AdvancedComplexGsonDemo | Enterprise | Gson | ✅ Full | ✅ Complete |
| AdvancedComplexKotlinxDemo | Enterprise | GenericSerializer | ⚠️ Limited | ✅ Error Handling |

## 📄 License

MIT License