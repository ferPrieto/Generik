plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "com.generik.casestudy"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.generik.scenario3.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
    implementation(project(":case-study:shared-models"))
    implementation(libs.kotlinx.serialization.json)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
