import Testing.FakeDataSource
import Testing.ViewMonthlySummeryChecker
import core.FinanceTrackerManager
import core.FinanceTrackerManagerImpl

fun main() {
    val financeTrackerManager = FinanceTrackerManagerImpl()
    val viewMonthlySummeryChecker = ViewMonthlySummeryChecker(financeTrackerManager)
    viewMonthlySummeryChecker.runAllMonthlySummaryChecks()

}