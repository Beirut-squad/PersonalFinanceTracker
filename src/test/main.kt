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

    println("*************** Start Test ***************")
    dataSource.clear()
    addChecker.runAddTests()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    deleteChecker.runAllDeleteChecker()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    editChecker.editTransactionCheck()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    balanceReportChecker.checkBalanceReport()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    getMonthlySummeryChecker.runAllMonthlySummaryChecks()
    println("*************** End Test ***************")
}