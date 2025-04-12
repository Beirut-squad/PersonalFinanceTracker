package test

import core.*
import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource


fun main() {
    val dataSource: FinanceTrackerDataSource = InMemoryDataSource()
    val validator: FinanceTrackerValidator = FinanceTrackerValidatorImp()
    val financeTrackerManager: FinanceTrackerManager = FinanceTrackerManagerImpl(dataSource, validator)
    val checker = Checker()

    val addChecker = AddTransactionChecker(financeTrackerManager,checker)

    val deleteChecker = DeleteTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(dataSource, validator) ,checker)

    val editChecker = EditTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(dataSource, validator), checker)

    val balanceReportChecker = BalanceReportChecker(
        financeTrackerManager = financeTrackerManager ,checker)

    val getMonthlySummeryChecker = GetMonthlySummeryChecker(
        financeTrackerManager = financeTrackerManager ,checker)

    println("*************** Start Test ***************")
    dataSource.clear()
    addChecker.runAddChecks()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    deleteChecker.runDeleteChecks()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    editChecker.runEditTransactionChecks()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    balanceReportChecker.runBalanceReportChecks()
    println("*************** End Test ***************")

    println("\n*************** Start Test ***************")
    dataSource.clear()
    getMonthlySummeryChecker.runMonthlySummaryChecks()
    println("*************** End Test ***************")
}