package core

import datasource.FinanceTrackerDataSource
import models.*
import java.util.*

class DefaultFinanceTrackerViewer(private val dataSource: FinanceTrackerDataSource) : FinanceTrackerViewer {

    override fun balanceReport(): TotalTransactions {
        // todo: implement methods to get balance reports
        return TotalTransactions(transactions = emptyList())
    }

}