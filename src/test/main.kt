package test

import core.FinanceTrackerManager
import core.FinanceTrackerManagerImpl
import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource


fun main() {




    val ftDataSource: FinanceTrackerDataSource = InMemoryDataSource()
    val financeTrackerManager: FinanceTrackerManager = FinanceTrackerManagerImpl(ftDataSource)
    val addChecker = AddTransactionChecker(financeTrackerManager)



    val deleteChecker = DeleteTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(ftDataSource)
    )
    val editChecker = EditTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(ftDataSource)
    )
    val viewChecker = FinanceTrackerViewerChecker(
        dataSource = FakeDataSource()
    )

    val viewMonthlySummeryChecker = GetMonthlySummeryChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(ftDataSource)
    )

    addChecker.runAddTests()
    deleteChecker.runAllDeleteChecker()
    editChecker.editTransactionCheck()
    viewChecker.checkBalanceReport()
      viewMonthlySummerChecker.runAllMonthlySummaryChecks()

}