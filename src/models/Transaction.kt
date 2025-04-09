package models

import java.util.Date
import java.util.UUID

// TODO: Implement
data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val amount: Double,
    val transactionType: TransactionType,
    val category: String,
    val date: Date
)

