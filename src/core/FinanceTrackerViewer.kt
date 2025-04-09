package core

import models.TotalTransactions
import models.Transaction

interface FinanceTrackerViewer {
    fun balanceReport(): TotalTransactions
}

