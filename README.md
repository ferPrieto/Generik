# Generik

![Generik Banner](art/banner.jpg)

Generik provides a powerful **GenericSerializer** class that eliminates the need for manual serialization and deserialization, helping developers write cleaner, more efficient code with less boilerplate.

## 🎯 Project Goals

Generik is both an educational playground and a powerful serialization toolkit built to:

- 🔍 **Showcase Generic Serialization**: Compare real-world strategies using Gson, Kotlinx Serialization, and custom solutions  
- 🧠 **Teach Through Practice**: Dive into complex data models with nested objects, collections, enums, and recursive types  
- ⚙️ **Harness Kotlin Reflection**: Experiment with reflection-based generic serializers — and understand their power and pitfalls  
- ⚖️ **Compare & Choose Wisely**: Gain insight into when to use Gson, Kotlinx, or roll your own with custom generics

### Prerequisites
- Java 21+ (configured with JVM Toolchain)
- Gradle 8.9 (handled by wrapper)

### 🧪 Test Coverage Summary

| Demo Class | Object Type | Serializer | Status | Test Coverage |
|------------|-------------|------------|---------|---------------|
| GsonSerializationDemo | Organization | Gson | ✅ Full | ✅ Complete |
| KotlinSerializationDemo | User | Built-in @Serializable | ✅ Full | ✅ Complete |
| ComplexGsonDemo | Organization | Gson | ✅ Full | ✅ Complete |
| ComplexKotlinxDemo | Organization | GenericSerializer | ⚠️ Limited | ✅ Error Handling |
| AdvancedComplexGsonDemo | Enterprise | Gson | ✅ Full | ✅ Complete |
| AdvancedComplexKotlinxDemo | Enterprise | GenericSerializer | ⚠️ Limited | ✅ Error Handling |

## 🚀 Future Improvements

The project currently demonstrates the limitations of reflection-based serialization in modern Java environments. Future improvements could include:

- **Enhanced Reflection Access**: Investigate workarounds for Java module system restrictions
- **Alternative Serialization Strategies**: Implement code generation or annotation processing approaches
- **Performance Optimization**: Optimize reflection usage and caching strategies
- **Error Recovery**: Implement fallback mechanisms when reflection fails on complex nested structures 

These improvements would make the GenericSerializer more robust while maintaining its educational value in demonstrating the challenges and solutions in generic serialization.

## 📄 License

MIT License

Copyright (c) 2025 Fernando Prieto Moyano

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

The MIT License is a permissive license that allows for commercial use, modification, distribution, and private use. This project is open-source and welcomes contributions from the community.
