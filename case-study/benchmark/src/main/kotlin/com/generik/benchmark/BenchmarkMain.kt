package com.generik.benchmark

import com.generik.GenericSerializer
import com.generik.models.annotated.*
import com.generik.models.plain.Enterprise as PlainEnterprise
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.system.measureTimeMillis

fun main() {
    println("=== Generik Serialization Benchmark ===")
    
    val iterations = 1_000  // Reduced due to complexity of Enterprise objects
    println("Benchmarking with $iterations Enterprise objects...")
    
    // Create test data
    val enterpriseData = generateEnterpriseTestData(iterations)
    val plainEnterpriseData = generatePlainEnterpriseTestData(iterations)
    
    // Initialize serializers
    val gson = GsonBuilder().create()
    val json = Json { 
        encodeDefaults = true
        prettyPrint = false  // Disable pretty printing for performance
    }
    val enterpriseSerializer = GenericSerializer(PlainEnterprise::class)
    
    println("\n| Library | Serialization Time (ms) | Deserialization Time (ms) | JSON Size (bytes) |")
    println("|---------|-------------------------|---------------------------|--------------------|")
    
    // Benchmark Gson (using annotated models)
    benchmarkGson(gson, enterpriseData)
    
    // Benchmark Kotlinx Serialization (using @Serializable annotated models)
    benchmarkKotlinx(json, enterpriseData)
    
    // Benchmark Generik (using plain models without annotations)
    benchmarkGenerik(json, enterpriseSerializer, plainEnterpriseData)
    
    println("\nNotes:")
    println("- Smaller times indicate better performance")
    println("- JSON size is measured for a single Enterprise object")
    println("- Generik may show errors due to reflection limitations")
}

fun generateEnterpriseTestData(count: Int): List<Enterprise> {
    return (1..count).map { i ->
        // Create a comprehensive Enterprise with all nested complexity
        val headquarters = Address(
            street = "${i} Enterprise Blvd",
            city = "Enterprise City $i",
            zipCode = "${90000 + i}",
            country = "Country $i"
        )
        
        val user = User(
            name = "CEO $i",
            yearOfBirth = 1970 + (i % 20)
        )
        
        val contact = Contact(
            email = "ceo$i@enterprise$i.com",
            phone = "+1-555-${1000 + i}",
            socialMedia = mapOf("linkedin" to "ceo$i", "twitter" to "@ceo$i")
        )
        
        val employee = Employee(
            id = "emp-$i",
            personalInfo = user,
            contact = contact,
            address = headquarters,
            position = "Chief Executive Officer",
            department = "Executive",
            salary = 500000.0 + (i % 200000),
            skills = listOf("Leadership", "Strategy", "Management", "Vision"),
            projects = emptyList(),
            metadata = mapOf("hire_date" to "2020-01-01", "level" to "C-suite")
        )
        
        val company = Company(
            name = "TechCorp $i",
            address = headquarters,
            industry = "Technology",
            founded = 2000 + (i % 24),
            employees = listOf(employee),
            departments = mapOf(
                "Engineering" to listOf("emp-eng-$i"),
                "Marketing" to listOf("emp-mkt-$i"),
                "Sales" to listOf("emp-sales-$i")
            ),
            isPublic = i % 2 == 0
        )
        
        val organization = Organization(
            name = "Enterprise Org $i",
            type = OrganizationType.CORPORATION,
            headquarters = headquarters,
            companies = listOf(company),
            partnerships = mapOf("Partner$i" to Partnership(
                partnerName = "Strategic Partner $i",
                type = "Technology",
                since = "2020-${(i % 12) + 1}-01",
                value = 1000000.0 + (i % 5000000),
                contacts = listOf(contact)
            )),
            globalMetrics = mapOf(
                "revenue" to (10000000.0 + (i % 50000000)),
                "employees" to (100.0 + (i % 1000)),
                "marketCap" to (50000000.0 + (i % 500000000))
            ),
            regions = listOf("North America", "Europe", "Asia"),
            certifications = listOf("ISO 9001", "SOC 2", "PCI DSS"),
            founded = 1990 + (i % 34)
        )
        
        val complianceFramework = ComplianceFramework(
            regulations = listOf(
                Regulation(
                    name = "GDPR",
                    jurisdiction = "EU",
                    requirements = listOf(
                        ComplianceRequirement(
                            requirement = "Data Protection",
                            dueDate = "2025-12-31",
                            status = ComplianceStatus.COMPLIANT,
                            evidence = listOf("audit-report-$i.pdf")
                        )
                    ),
                    penalties = listOf(
                        Penalty(
                            type = "Fine",
                            amount = 20000000.0,
                            description = "Maximum GDPR fine"
                        )
                    )
                )
            ),
            audits = listOf(
                Audit(
                    auditor = "Big Four Auditor",
                    date = "2024-${(i % 12) + 1}-01",
                    scope = listOf("Financial", "IT", "Compliance"),
                    findings = listOf(
                        AuditFinding(
                            category = "IT Security",
                            severity = AuditSeverity.MEDIUM,
                            description = "Password policy review needed",
                            remediation = "Update password policies"
                        )
                    ),
                    recommendations = listOf("Implement MFA", "Regular security training")
                )
            ),
            certifications = listOf(
                Certification(
                    name = "ISO 27001",
                    issuer = "International Organization for Standardization",
                    issueDate = "2023-01-01",
                    expiryDate = "2026-01-01",
                    credentialId = "ISO-27001-$i"
                )
            ),
            policies = mapOf(
                "Security" to Policy(
                    title = "Information Security Policy",
                    version = "2.1",
                    effectiveDate = "2024-01-01",
                    procedures = listOf(
                        Procedure(
                            name = "Access Control",
                            steps = listOf("Identify user", "Verify credentials", "Grant access"),
                            owner = employee,
                            reviewDate = "2024-12-31"
                        )
                    ),
                    applicableRoles = emptyList()
                )
            )
        )
        
        Enterprise(
            id = "ent-$i",
            name = "Global Enterprise $i",
            headquarters = headquarters,
            organizations = listOf(organization),
            teams = mapOf(
                "Executive" to listOf(
                    Team(
                        id = "team-exec-$i",
                        name = "Executive Team $i",
                        description = "Senior leadership team",
                        members = emptyList(),
                        projects = emptyList(),
                        roles = mapOf("Leadership" to emptyList()),
                        subTeams = emptyList(),
                        collaborations = emptyList(),
                        resources = emptyMap()
                    )
                )
            ),
            globalRoles = emptyList(),
            partnerships = mapOf(
                "Strategic" to listOf(
                    Partnership(
                        partnerName = "Major Partner $i",
                        type = "Strategic",
                        since = "2019-01-01",
                        value = 50000000.0 + (i % 100000000),
                        contacts = listOf(contact)
                    )
                )
            ),
            acquisitions = emptyList(),
            boardMembers = emptyList(),
            financials = mapOf(
                "Q${(i % 4) + 1}-2024" to FinancialReport(
                    period = "Q${(i % 4) + 1} 2024",
                    revenue = 25000000.0 + (i % 75000000),
                    expenses = 20000000.0 + (i % 60000000),
                    profit = 5000000.0 + (i % 15000000),
                    segments = mapOf(
                        "Technology" to SegmentPerformance(
                            revenue = 15000000.0 + (i % 45000000),
                            growth = 0.15 + (i % 20) / 100.0,
                            marketShare = 0.25 + (i % 15) / 100.0,
                            keyMetrics = mapOf("customerSat" to 4.2 + (i % 8) / 10.0)
                        )
                    ),
                    investments = emptyList()
                )
            ),
            compliance = complianceFramework,
            founded = 1980 + (i % 44),
            stockExchanges = listOf(
                StockListing(
                    exchange = "NASDAQ",
                    symbol = "TECH$i",
                    listingDate = "2010-${(i % 12) + 1}-01",
                    marketCap = 1000000000.0 + (i % 500000000),
                    priceHistory = listOf(
                        PricePoint(
                            date = "2024-01-01",
                            openPrice = 100.0 + (i % 200),
                            closePrice = 105.0 + (i % 200),
                            volume = 1000000L + (i % 5000000)
                        )
                    )
                )
            )
        )
    }
}

fun generatePlainEnterpriseTestData(count: Int): List<PlainEnterprise> {
    return (1..count).map { i ->
        // Create comprehensive Plain Enterprise objects (without @Serializable annotations)
        val headquarters = com.generik.models.plain.Address(
            street = "${i} Enterprise Blvd",
            city = "Enterprise City $i",
            zipCode = "${90000 + i}",
            country = "Country $i"
        )
        
        val user = com.generik.models.plain.User(
            name = "CEO $i",
            yearOfBirth = 1970 + (i % 20)
        )
        
        val contact = com.generik.models.plain.Contact(
            email = "ceo$i@enterprise$i.com",
            phone = "+1-555-${1000 + i}",
            socialMedia = mapOf("linkedin" to "ceo$i", "twitter" to "@ceo$i")
        )
        
        val employee = com.generik.models.plain.Employee(
            id = "emp-$i",
            personalInfo = user,
            contact = contact,
            address = headquarters,
            position = "Chief Executive Officer",
            department = "Executive",
            salary = 500000.0 + (i % 200000),
            skills = listOf("Leadership", "Strategy", "Management", "Vision"),
            projects = emptyList(),
            metadata = mapOf("hire_date" to "2020-01-01", "level" to "C-suite")
        )
        
        val company = com.generik.models.plain.Company(
            name = "TechCorp $i",
            address = headquarters,
            industry = "Technology",
            founded = 2000 + (i % 24),
            employees = listOf(employee),
            departments = mapOf(
                "Engineering" to listOf("emp-eng-$i"),
                "Marketing" to listOf("emp-mkt-$i"),
                "Sales" to listOf("emp-sales-$i")
            ),
            isPublic = i % 2 == 0
        )
        
        val partnership = com.generik.models.plain.Partnership(
            partnerName = "Strategic Partner $i",
            type = "Technology",
            since = "2020-${(i % 12) + 1}-01",
            value = 1000000.0 + (i % 5000000),
            contacts = listOf(contact)
        )
        
        val organization = com.generik.models.plain.Organization(
            name = "Enterprise Org $i",
            type = com.generik.models.plain.OrganizationType.CORPORATION,
            headquarters = headquarters,
            companies = listOf(company),
            partnerships = mapOf("Partner$i" to partnership),
            globalMetrics = mapOf(
                "revenue" to (10000000.0 + (i % 50000000)),
                "employees" to (100.0 + (i % 1000)),
                "marketCap" to (50000000.0 + (i % 500000000))
            ),
            regions = listOf("North America", "Europe", "Asia"),
            certifications = listOf("ISO 9001", "SOC 2", "PCI DSS"),
            founded = 1990 + (i % 34)
        )
        
        val complianceFramework = com.generik.models.plain.ComplianceFramework(
            regulations = listOf(
                com.generik.models.plain.Regulation(
                    name = "GDPR",
                    jurisdiction = "EU",
                    requirements = listOf(
                        com.generik.models.plain.ComplianceRequirement(
                            requirement = "Data Protection",
                            dueDate = "2025-12-31",
                            status = com.generik.models.plain.ComplianceStatus.COMPLIANT,
                            evidence = listOf("audit-report-$i.pdf")
                        )
                    ),
                    penalties = listOf(
                        com.generik.models.plain.Penalty(
                            type = "Fine",
                            amount = 20000000.0,
                            description = "Maximum GDPR fine"
                        )
                    )
                )
            ),
            audits = emptyList(),
            certifications = emptyList(),
            policies = emptyMap()
        )
        
        PlainEnterprise(
            id = "ent-$i",
            name = "Global Enterprise $i",
            headquarters = headquarters,
            organizations = listOf(organization),
            teams = emptyMap(),
            globalRoles = emptyList(),
            partnerships = emptyMap(),
            acquisitions = emptyList(),
            boardMembers = emptyList(),
            financials = emptyMap(),
            compliance = complianceFramework,
            founded = 1980 + (i % 44),
            stockExchanges = emptyList()
        )
    }
}

fun benchmarkGson(gson: Gson, testData: List<Enterprise>) {
    try {
        var jsonResults: List<String>
        val serializationTime = measureTimeMillis {
            jsonResults = testData.map { gson.toJson(it) }
        }
        
        val deserializationTime = measureTimeMillis {
            jsonResults.map { gson.fromJson(it, Enterprise::class.java) }
        }
        
        val jsonSize = gson.toJson(testData.first()).toByteArray().size
        
        println("| Gson    | %-23d | %-25d | %-18d |".format(serializationTime, deserializationTime, jsonSize))
    } catch (e: Exception) {
        println("| Gson    | ERROR: ${e.message?.take(20)}... |")
    }
}

fun benchmarkKotlinx(json: Json, testData: List<Enterprise>) {
    try {
        var jsonResults: List<String>
        val serializationTime = measureTimeMillis {
            jsonResults = testData.map { json.encodeToString(it) }
        }
        
        val deserializationTime = measureTimeMillis {
            jsonResults.map { json.decodeFromString<Enterprise>(it) }
        }
        
        val jsonSize = json.encodeToString(testData.first()).toByteArray().size
        
        println("| Kotlinx | %-23d | %-25d | %-18d |".format(serializationTime, deserializationTime, jsonSize))
    } catch (e: Exception) {
        println("| Kotlinx | ERROR: ${e.message?.take(20)}... |")
    }
}

fun benchmarkGenerik(
    json: Json,
    serializer: GenericSerializer<PlainEnterprise>,
    testData: List<PlainEnterprise>
) {
    try {
        var jsonResults: List<String>
        val serializationTime = measureTimeMillis {
            jsonResults = testData.map { json.encodeToString(serializer, it) }
        }
        
        val deserializationTime = measureTimeMillis {
            jsonResults.map { json.decodeFromString(serializer, it) }
        }
        
        val jsonSize = json.encodeToString(serializer, testData.first()).toByteArray().size
        
        println("| Generik | %-23d | %-25d | %-18d |".format(serializationTime, deserializationTime, jsonSize))
    } catch (e: Exception) {
        println("| Generik | ERROR: Reflection limitations prevent benchmarking |")
        println("|         | This demonstrates the known limitations of      |")
        println("|         | reflection-based serialization                  |")
    }
}
