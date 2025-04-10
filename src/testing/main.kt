import testing.DeleteTransactionChecker
import testing.EditTransactionChecker
import core.FinanceTrackerManagerImpl
import testing.FakeDataSource


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


    addChecker.runAddTests()
    deleteChecker.runAllDeleteChecker()
    editChecker.editTransactionCheck()
    viewChecker.checkBalanceReport()

}