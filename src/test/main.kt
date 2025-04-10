package test

import core.FinanceTrackerManager
import core.FinanceTrackerManagerImpl
import core.FinanceTrackerValidator
import core.FinanceTrackerValidatorImp
import datasource.FinanceTrackerDataSource
import datasource.InMemoryDataSource


fun main() {




    val ftDataSource: FinanceTrackerDataSource = InMemoryDataSource()
    val validator: FinanceTrackerValidator = FinanceTrackerValidatorImp()
    val financeTrackerManager: FinanceTrackerManager = FinanceTrackerManagerImpl(ftDataSource, validator)
    val addChecker = AddTransactionChecker(financeTrackerManager)



    val deleteChecker = DeleteTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(ftDataSource, validator)
    )
    val editChecker = EditTransactionChecker(
        financeTrackerManager = FinanceTrackerManagerImpl(ftDataSource, validator)
    )
    val viewChecker = FinanceTrackerViewerChecker(
        dataSource = FakeDataSource()
    )


    addChecker.runAddTests()
    deleteChecker.runAllDeleteChecker()
    editChecker.editTransactionCheck()
    viewChecker.checkBalanceReport()

}