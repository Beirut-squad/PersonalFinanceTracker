package data.models

import java.util.Date

data class Transaction(
    val id: Int,
    val amount: Double,
    val transactionType: TransactionType,
    val date: Date,
    val category: String, // TODO: Convert to enum or not
)