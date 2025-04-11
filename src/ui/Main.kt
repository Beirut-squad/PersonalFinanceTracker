package ui

import core.FinanceTrackerManagerImpl
import core.FinanceTrackerValidator
import core.FinanceTrackerValidatorImp
import datasource.CsvDataSource
import datasource.utils.CsvParser

fun main() {
    val dataSource = CsvDataSource(
        csvParser = CsvParser()
    )
    val validator: FinanceTrackerValidator = FinanceTrackerValidatorImp()
    val manager = FinanceTrackerManagerImpl(dataSource,validator)
    val cli = FinanceTrackerCLI(manager)
    cli.run()
}