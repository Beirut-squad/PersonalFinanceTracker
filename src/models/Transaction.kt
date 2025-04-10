package models

import java.util.Date
import java.util.UUID
data class Transaction(
    val id: Int,
    val title: String,
    val amount: Double,
    val transactionType: TransactionType,
    val category: Category,
    val date: Date
)
