package test

import core.*
import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource


fun main() {
    val dataSource: FinanceTrackerDataSource = InMemoryDataSource()
    val validator: FinanceTrackerValidator = FinanceTrackerValidatorImp()
    val financeTrackerManager: FinanceTrackerManager = FinanceTrackerManagerImpl(dataSource, validator)

    val addChecker = AddTransactionChecker(financeTrackerManager)
    val deleteChecker = DeleteTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(dataSource, validator)
    )
    val editChecker = EditTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(dataSource, validator)
    )
    val balanceReportChecker = BalanceReportChecker(
        financeTrackerManager = financeTrackerManager
    )

    val getMonthlySummeryChecker = GetMonthlySummeryChecker(
        financeTrackerManager = financeTrackerManager
    )

    dataSource.clear()
    addChecker.runAddTests()

    dataSource.clear()
    deleteChecker.runAllDeleteChecker()

    dataSource.clear()
    editChecker.editTransactionCheck()

    dataSource.clear()
    balanceReportChecker.checkBalanceReport()

    dataSource.clear()
    getMonthlySummeryChecker.runAllMonthlySummaryChecks()
}