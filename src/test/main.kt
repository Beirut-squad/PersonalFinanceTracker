package test

import core.FinanceTrackerManager
import core.FinanceTrackerManagerImpl
import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource
import models.Category
import models.Transaction
import models.TransactionType
import java.util.Date


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


    addChecker.runAddTests()
    deleteChecker.runAllDeleteChecker()
    editChecker.editTransactionCheck()
    viewChecker.checkBalanceReport()

}