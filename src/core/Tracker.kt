package core

import data.models.Transaction

interface Tracker {
    fun add(transaction: Transaction)

    fun view(): List<Transaction>

    fun edit(transaction: Transaction)

    fun delete(transaction: Transaction)
}