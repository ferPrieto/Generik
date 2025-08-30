// Root build configuration for multi-module project
plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

group = "com.generik"
version = "1.0-SNAPSHOT"

subprojects {
    repositories {
        mavenCentral()
    }
    
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
} 
