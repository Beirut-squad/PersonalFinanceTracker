package core

import models.*
import java.util.*

class DefaultFinanceTrackerViewer : FinanceTrackerViewer {

    override fun balanceReport(): TotalTransactions {
        // todo: implement methods to get balance reports
        return TotalTransactions(transactions = emptyList())
    }

}