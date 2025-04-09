import core.FinanceTrackerManagerImpl

fun main() {
    val checker = TrackingManagerChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )

    checker.runAllTests()
}