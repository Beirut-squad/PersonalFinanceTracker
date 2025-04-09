package models

import java.util.Date

data class Transaction(
    val id: Int,
    val title: String,
    val amount: Double,
    val transActionType: TransactionType,
    val category: Category,
    val date: Date
)