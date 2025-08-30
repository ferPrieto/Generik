# Shared Models

This module contains the comprehensive data models used throughout the Generik project. The models are organized into two distinct packages to demonstrate different serialization approaches.

## 📁 Package Structure

### `com.generik.models.annotated`
Models with `@Serializable` annotations for use with compile-time serialization libraries:
- **Used by**: Gson scenarios, Kotlinx Serialization scenarios, Gson demos
- **Purpose**: Demonstrates traditional serialization approaches
- **Benefits**: Optimized performance, type safety, compile-time validation
- **Requirements**: Kotlinx Serialization dependency

### `com.generik.models.plain`  
Plain Kotlin data classes without any annotations for reflection-based serialization:
- **Used by**: GenericSerializer scenarios and demos
- **Purpose**: Demonstrates annotation-free serialization using pure reflection
- **Benefits**: Zero boilerplate, works with any data class, no compile-time dependencies
- **Trade-offs**: Runtime performance overhead, potential reflection restrictions

## 🎯 Educational Purpose

This dual-package architecture serves several educational goals:

### **1. Clear Separation of Concerns**
- **Annotated models** show how modern serialization libraries leverage compile-time information
- **Plain models** demonstrate the power and limitations of pure runtime reflection

### **2. Performance Comparison**
- Benchmark results clearly show the performance impact of different approaches
- Identical data structures enable fair comparison between serialization strategies

### **3. Real-World Complexity**
Both packages contain the same sophisticated enterprise-grade models:
- **Enterprise**: Complex corporate hierarchies with acquisitions and compliance
- **Organization**: Multi-company structures with partnerships and metrics
- **Employee**: Detailed staff profiles with skills, projects, and performance data
- **Team**: Collaborative structures with roles, responsibilities, and resources
- **Project**: Development initiatives with status tracking and budgets
- **Role & Permissions**: Security framework with inheritance and conditions

### **4. Annotation Impact Demonstration**
Side-by-side comparison shows:
- **With annotations**: Better IDE support, compile-time safety, optimized performance
- **Without annotations**: Simpler model definitions, more flexible, reflection-dependent

## 🔍 Model Complexity Features

The models showcase advanced serialization challenges:

- **📊 Nested Objects**: Address → Company → Employee hierarchies
- **📋 Collections**: Lists of employees, projects, skills
- **🗺️ Maps**: Department structures, metrics, configurations
- **🔗 Circular References**: Teams referencing team members, roles inheriting roles
- **🏷️ Enums**: Status values, types, levels for type safety
- **❓ Nullable Properties**: Optional fields demonstrating null handling
- **🔄 Recursive Structures**: Deliverables containing sub-deliverables
- **📈 Complex Business Logic**: Financial reports, compliance frameworks, audit trails

## 🚀 Usage Examples

### Annotated Models (Gson/Kotlinx)
```kotlin
import com.generik.models.annotated.*

// Works with compile-time serialization
val enterprise = Enterprise(...)
val json = Json.encodeToString(enterprise)
```

### Plain Models (GenericSerializer)
```kotlin
import com.generik.models.plain.*

// Works with reflection-based serialization
val enterprise = Enterprise(...)
val serializer = GenericSerializer(Enterprise::class)
val json = Json.encodeToString(serializer, enterprise)
```

## 📊 Performance Characteristics

Based on Enterprise object benchmarking:

| Approach | Serialization | Deserialization | JSON Size | Annotations Required |
|----------|---------------|-----------------|-----------|---------------------|
| **Gson + Annotated** | 368ms ⚡ | 267ms ⚡ | 4,076 bytes | ❌ None |
| **Kotlinx + Annotated** | 393ms | 261ms ⚡ | 4,076 bytes | ✅ @Serializable |
| **GenericSerializer + Plain** | 1,400ms | 558ms | 2,029 bytes | ❌ None |

## 🎓 Learning Outcomes

This architecture teaches:

1. **Annotation Trade-offs**: When to use compile-time vs runtime approaches
2. **Performance Impact**: Quantified costs of reflection-based serialization
3. **Flexibility vs Speed**: How annotations improve performance but reduce flexibility
4. **Real-World Application**: Which approach fits different project requirements
5. **Architecture Decisions**: How to structure projects for multiple serialization strategies

The shared-models module serves as both a comprehensive data model library and an educational framework for understanding modern serialization approaches in Kotlin.
