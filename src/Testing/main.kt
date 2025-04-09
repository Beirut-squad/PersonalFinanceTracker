import Testing.EditTransactionChecker
import core.FinanceTrackerManagerImpl

/*
fun main() {
    val checker = TrackingManagerChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )

    checker.runAllTests()
}*/

fun main() {
    val checker = EditTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )

    checker.editTransactionCheck()
}