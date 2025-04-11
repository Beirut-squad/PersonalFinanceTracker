package ui

import core.FinanceTrackerManagerImpl
import core.FinanceTrackerValidator
import core.FinanceTrackerValidatorImp
import datasource.InMemoryDataSource

fun main() {
    val dataSource = InMemoryDataSource()
    val validator: FinanceTrackerValidator = FinanceTrackerValidatorImp()
    val manager = FinanceTrackerManagerImpl(dataSource,validator)
    val cli = FinanceTrackerCLI(manager)
    cli.run()
}