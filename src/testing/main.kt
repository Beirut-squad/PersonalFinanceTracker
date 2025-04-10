import testing.ViewMonthlySummeryChecker
import core.FinanceTrackerManagerImpl

fun main() {
    val financeTrackerManager = FinanceTrackerManagerImpl()
    val viewMonthlySummeryChecker = ViewMonthlySummeryChecker(financeTrackerManager)
    viewMonthlySummeryChecker.runAllMonthlySummaryChecks()

}