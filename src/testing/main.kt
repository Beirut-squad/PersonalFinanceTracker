package testing

import AddTransactionChecker
import FinanceTrackerViewerChecker
import core.FinanceTrackerManagerImpl


fun main() {
    val addChecker = AddTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )
    val deleteChecker = DeleteTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )
    val editChecker = EditTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )
    val viewChecker = FinanceTrackerViewerChecker(
        dataSource = FakeDataSource()
    )

    val viewMonthlySummeryChecker = ViewMonthlySummeryChecker(
        financeTrackerManager = FinanceTrackerManagerImpl()
    )

    addChecker.runAddTests()
    deleteChecker.runAllDeleteChecker()
    editChecker.editTransactionCheck()
    viewChecker.checkBalanceReport()
    viewMonthlySummeryChecker.runAllMonthlySummaryChecks()

}