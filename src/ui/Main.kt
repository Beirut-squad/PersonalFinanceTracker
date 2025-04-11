package ui

import core.FinanceTrackerManagerImpl
import core.FinanceTrackerValidator
import core.FinanceTrackerValidatorImp
import datasource.FilesDataSource
import datasource.InMemoryDataSource

fun main() {
    val dataSource = FilesDataSource()
    val validator: FinanceTrackerValidator = FinanceTrackerValidatorImp()
    val manager = FinanceTrackerManagerImpl(dataSource,validator)
    val cli = FinanceTrackerCLI(manager)
    cli.run()
}