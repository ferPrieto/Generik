package com.generik.models.plain

data class Enterprise(
    val id: String,
    val name: String,
    val headquarters: Address,
    val organizations: List<Organization>, 
    val teams: Map<String, List<Team>>,  
    val globalRoles: List<Role>,
    val partnerships: Map<String, List<Partnership>>,  
    val acquisitions: List<Acquisition> = emptyList(),
    val boardMembers: List<BoardMember> = emptyList(),
    val financials: Map<String, FinancialReport> = emptyMap(),
    val compliance: ComplianceFramework,
    val founded: Int,
    val stockExchanges: List<StockListing> = emptyList()
)

data class Acquisition(
    val targetCompany: Company,
    val acquiredDate: String,
    val purchasePrice: Double,
    val integrationPlan: IntegrationPlan,
    val synergies: List<Synergy> = emptyList(),
    val retainedPersonnel: List<Employee> = emptyList()
)

data class IntegrationPlan(
    val phases: List<IntegrationPhase>,
    val timeline: String,
    val budget: Double,
    val riskAssessments: Map<String, RiskAssessment> = emptyMap()
)

data class IntegrationPhase(
    val name: String,
    val startDate: String,
    val endDate: String,
    val deliverables: List<Deliverable>,
    val dependencies: List<String> = emptyList()
)

data class Deliverable(
    val name: String,
    val description: String,
    val owner: Employee,
    val status: DeliverableStatus,
    val subDeliverables: List<Deliverable> = emptyList()  // Recursive collection
)

data class Synergy(
    val type: SynergyType,
    val description: String,
    val expectedValue: Double,
    val realizationDate: String,
    val dependencies: List<String> = emptyList()
)

data class RiskAssessment(
    val riskType: String,
    val probability: Double,
    val impact: Double,
    val mitigationStrategies: List<MitigationStrategy> = emptyList()
)

data class MitigationStrategy(
    val strategy: String,
    val cost: Double,
    val effectiveness: Double,
    val owner: Employee
)

data class BoardMember(
    val person: User,
    val position: String,
    val appointedDate: String,
    val committees: List<Committee> = emptyList(),
    val expertise: List<Skill> = emptyList(),
    val shareholding: Shareholding?
)

data class Committee(
    val name: String,
    val purpose: String,
    val members: List<BoardMember>,  // Circular reference
    val meetings: List<Meeting> = emptyList()
)

data class Meeting(
    val date: String,
    val agenda: List<AgendaItem>,
    val attendees: List<BoardMember>,
    val decisions: List<Decision> = emptyList()
)

data class AgendaItem(
    val title: String,
    val presenter: BoardMember,
    val duration: Int,
    val materials: List<String> = emptyList()
)

data class Decision(
    val title: String,
    val description: String,
    val votingResults: Map<String, Vote>,
    val implementationPlan: List<String> = emptyList()
)

data class Vote(
    val member: String,
    val decision: VoteDecision,
    val comments: String?
)

data class Shareholding(
    val shares: Long,
    val percentage: Double,
    val acquisitionDate: String,
    val transactions: List<ShareTransaction> = emptyList()
)

data class ShareTransaction(
    val date: String,
    val type: TransactionType,
    val shares: Long,
    val pricePerShare: Double
)

data class FinancialReport(
    val period: String,
    val revenue: Double,
    val expenses: Double,
    val profit: Double,
    val segments: Map<String, SegmentPerformance>,
    val investments: List<Investment> = emptyList()
)

data class SegmentPerformance(
    val revenue: Double,
    val growth: Double,
    val marketShare: Double,
    val keyMetrics: Map<String, Double> = emptyMap()
)

data class Investment(
    val target: String,
    val amount: Double,
    val type: InvestmentType,
    val expectedReturns: List<ReturnProjection> = emptyList()
)

data class ReturnProjection(
    val year: Int,
    val projectedReturn: Double,
    val confidence: Double
)

data class ComplianceFramework(
    val regulations: List<Regulation>,
    val audits: List<Audit> = emptyList(),
    val certifications: List<Certification> = emptyList(),
    val policies: Map<String, Policy> = emptyMap()
)

data class Regulation(
    val name: String,
    val jurisdiction: String,
    val requirements: List<ComplianceRequirement>,
    val penalties: List<Penalty> = emptyList()
)

data class ComplianceRequirement(
    val requirement: String,
    val dueDate: String,
    val status: ComplianceStatus,
    val evidence: List<String> = emptyList()
)

data class Penalty(
    val type: String,
    val amount: Double,
    val description: String
)

data class Audit(
    val auditor: String,
    val date: String,
    val scope: List<String>,
    val findings: List<AuditFinding> = emptyList(),
    val recommendations: List<String> = emptyList()
)

data class AuditFinding(
    val category: String,
    val severity: AuditSeverity,
    val description: String,
    val remediation: String
)

data class Policy(
    val title: String,
    val version: String,
    val effectiveDate: String,
    val procedures: List<Procedure> = emptyList(),
    val applicableRoles: List<Role> = emptyList()
)

data class Procedure(
    val name: String,
    val steps: List<String>,
    val owner: Employee,
    val reviewDate: String
)

data class StockListing(
    val exchange: String,
    val symbol: String,
    val listingDate: String,
    val marketCap: Double,
    val priceHistory: List<PricePoint> = emptyList()
)

data class PricePoint(
    val date: String,
    val openPrice: Double,
    val closePrice: Double,
    val volume: Long
)

enum class DeliverableStatus {
    NOT_STARTED, IN_PROGRESS, COMPLETED, DELAYED, CANCELLED
}

enum class SynergyType {
    COST_REDUCTION, REVENUE_ENHANCEMENT, OPERATIONAL_EFFICIENCY, MARKET_EXPANSION
}

enum class VoteDecision {
    FOR, AGAINST, ABSTAIN
}

enum class TransactionType {
    BUY, SELL, DIVIDEND, SPLIT, MERGER
}

enum class InvestmentType {
    EQUITY, DEBT, REAL_ESTATE, COMMODITIES, DERIVATIVES
}

enum class ComplianceStatus {
    COMPLIANT, NON_COMPLIANT, PENDING, UNDER_REVIEW
}

enum class AuditSeverity {
    LOW, MEDIUM, HIGH, CRITICAL
}
