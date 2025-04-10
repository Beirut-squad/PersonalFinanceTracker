package models

import java.time.LocalDate
import java.util.Date
import java.util.UUID
data class Transaction(
    val id: Int = getNextId(),
    val title: String,
    val amount: Double,
    val transactionType: TransactionType,
    val category: Category,
    val date: Date = Date()
){
    companion object {
        private var lastId = 0

        fun getNextId(): Int {
            lastId++
            return lastId-1
        }
    }

}
