package ui

import Colors
import core.FinanceTrackerManager
import models.Category
import models.Transaction
import models.TransactionType
import java.time.LocalDate
import java.util.*


class FinanceTrackerCLI(private val manager: FinanceTrackerManager) {
    private var running = true
    private var check:String? = null

    fun run() {
        while (running) {
            println(Constants.MAIN_MENU_TITLE.message)
            println(Constants.MAIN_MENU_OPTION_1.message)
            println(Constants.MAIN_MENU_OPTION_2.message)
            println(Constants.MAIN_MENU_OPTION_3.message)
            println(Constants.MAIN_MENU_OPTION_4.message)
            print(Constants.MAIN_MENU_PROMPT.message)

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> transactionsMenu()
                2 -> viewMonthlySummary()
                3 -> viewBalanceReport()
                4 -> {
                    println(Constants.GOODBYE.message)
                    running = false
                }

                else -> {
                    Colors().printRedColorText(Constants.INVALID_CHOICE.message)
                }
            }
        }
    }

    private fun transactionsMenu() {
        while (true) {
            println(Constants.TRANSACTION_MENU_TITLE.message)
            println(Constants.TRANSACTION_MENU_OPTION_1.message)
            println(Constants.TRANSACTION_MENU_OPTION_2.message)
            println(Constants.TRANSACTION_MENU_OPTION_3.message)
            println(Constants.TRANSACTION_MENU_OPTION_4.message)
            println(Constants.TRANSACTION_MENU_OPTION_5.message)
            print(Constants.TRANSACTION_MENU_PROMPT.message)

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> addTransaction()
                2 -> editTransaction()
                3 -> deleteTransaction()
                4 -> viewTransactions()
                5 -> {
                    break
                }
                else -> Colors().printRedColorText(Constants.INVALID_CHOICE.message)
            }
        }
    }

    private fun addTransaction() {
        while (true) {
            println(Constants.ADD_TRANSACTION_TITLE.message)
            print(Constants.ENTER_TITLE.message)
            val title = readlnOrNull().orEmpty()

            print(Constants.ENTER_AMOUNT.message)
            val amount = readlnOrNull()?.toDoubleOrNull() ?: 0.0
            val type = getValidTransactionType()
            val category = getValidCategory()
            val transaction = Transaction(
                title = title,
                amount = amount,
                transactionType = type,
                category = category
            )

            if (manager.addTransaction(transaction)) {
                Colors().printGreenColorText(Constants.TRANSACTION_ADDED_SUCCESSFULLY.message)
                Colors().blueStars()
                break
            } else {
                Colors().printRedColorText(Constants.TRANSACTION_ADD_FAILED.message)
            }
        }

    }

    private fun getValidTransactionType(): TransactionType {
        while (true) {
            println(Constants.CHOOSE_TRANSACTION_TYPE.message)
            print(Constants.TRANSACTION_TYPE_PROMPT.message)
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> return TransactionType.INCOME
                2 -> return TransactionType.EXPENSE
                else -> Colors().printRedColorText(Constants.TRANSACTION_TYPE_INVALID.message)
            }
        }
    }

    private fun getValidCategory(): Category {
        while (true) {
            println(Constants.CHOOSE_CATEGORY.message)
            for (categoryInd in 1..Category.entries.size) {
                val category = Category.entries[categoryInd - 1]
                Colors().printPurpleColorText(
                    "\t\t${categoryInd}: " +
                            category.toString().lowercase().replaceFirstChar { it.uppercase() })
            }
            print("${Constants.CATEGORY_PROMPT.message}${Category.entries.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..Category.entries.size -> return Category.entries[(choice ?: 1) - 1]
                else -> Colors().printRedColorText(
                    "${Constants.CATEGORY_INVALID_INPUT.message}${Category.entries.size}."
                )
            }
        }
    }

    private fun editTransaction() {
        val transactions = manager.getTransactions()
        while (true) {
            if (transactions.isEmpty()) {
                break
            }
            viewTransactions(transactions)
            println(Constants.CHOOSE_TRANSACTION_TO_EDIT.message)
            print("${Constants.CATEGORY_PROMPT.message} ${transactions.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..transactions.size -> {
                    println(Constants.EDIT_TRANSACTION_TITLE.message)
                    var currentTransaction = transactions[(choice ?: 1) - 1]
                    currentTransaction = editTransactionTitle(currentTransaction)
                    currentTransaction = editTransactionAmount(currentTransaction)
                    currentTransaction = editTransactionType(currentTransaction)
                    currentTransaction = editTransactionCategory(currentTransaction)
                    if (manager.editTransaction(currentTransaction)) {
                        Colors().printGreenColorText(Constants.TRANSACTION_EDITED_SUCCESSFULLY.message)
                        Colors().blueStars()
                        break
                    } else {
                        Colors().printRedColorText(Constants.TRANSACTION_ADD_FAILED.message)
                        Colors().blueStars()
                    }
                }
                else -> {
                    Colors().printRedColorText(
                        "${Constants.CATEGORY_INVALID_INPUT.message}${transactions.size}."
                    )
                }
            }
        }
    }

    private fun deleteTransaction() {
        val transactions = manager.getTransactions()
        if (transactions.isEmpty()) {
            Colors().printRedColorText(Constants.TRANSACTION_DELETE_FAILED.message)
            return
        }
        while (true) {
            viewTransactions(transactions)
            println(Constants.CHOOSE_TRANSACTION_TO_DELETE.message)
            print("${Constants.CATEGORY_PROMPT.message} ${transactions.size}): ")
            when (val choice = readlnOrNull()?.toIntOrNull()) {
                in 1..transactions.size -> {
                    println(Constants.DELETE_TRANSACTION_TITLE.message)
                    val currentTransactionID = transactions[(choice ?: 1) - 1].id
                    if (manager.deleteTransaction(currentTransactionID)) {
                        Colors().printGreenColorText(Constants.TRANSACTION_DELETED_SUCCESSFULLY.message)
                        Colors().blueStars()
                        break
                    }
                }
                else -> Colors().printRedColorText(
                    "${Constants.CATEGORY_INVALID_INPUT.message}${transactions.size}."
                )
            }
        }
    }

    private fun viewTransactions(
        transactions: List<Transaction> = manager.getTransactions()
    ) {

        if (transactions.isEmpty()) {
            Colors().printRedColorText(Constants.NO_TRANSACTIONS.message)
        }

        for (transactionInd in 1..transactions.size) {
            val transaction = transactions[transactionInd - 1]
            Colors().printPurpleColorText(
                "\t${transactionInd}: " +
                        "${Constants.TITLE.message}: ${transaction.title}, ${Constants.AMOUNT.message}: ${transaction.amount}, ${Constants.TYPE.message}: ${
                            transaction.transactionType.toString().lowercase().replaceFirstChar
                            { it.uppercase() }
                        }, ${Constants.CATEGORY.message}: ${
                            transaction.category.toString()
                                .lowercase().replaceFirstChar { it.uppercase() }
                        }," +
                        " ${Constants.DATE.message}: ${transaction.date}"
            )
        }
    }

    private fun viewMonthlySummary() {
        print(Constants.ENTER_MONTH.message)
        val month = readlnOrNull()?.toIntOrNull() ?: 0

        if (checkMonth(month)){
            print(Constants.ENTER_YEAR.message)
            val year = readlnOrNull()?.toIntOrNull() ?: 0

            if (checkYear(year)) {
                viewTransactionsInMonth(month, year)
            } else {
                Colors().printRedColorText(Constants.INVALID_MONTH.message)
            }
        }else
            Colors().printRedColorText(Constants.INVALID_YEAR.message)
    }

    private fun viewBalanceReport() {
        val totalTransactions = manager.getBalanceReport()
        while (true) {
            viewTransactions(totalTransactions.transactions)
            if (totalTransactions.transactions.isEmpty()) {
                break
            }
            break
        }
        Colors().printGreenColorText(Constants.SHOW_BALANCE.message)
        Colors().printGreenColorText(
            Constants.BALANCE_DETAILS.message +
                    "${totalTransactions.totalBalance}\n${Constants.INCOME_BALANCE_DETAILS.message}" +
                    "${totalTransactions.incomeBalance}\n${Constants.EXPENSES_BALANCE_DETAILS.message}" +
                    "${totalTransactions.expensesBalance}"
        )
        Colors().blueStars()
    }

    private fun checkMonth(month: Int): Boolean {
        return month in 1..12
    }


    private fun editTransactionTitle(currentTransaction: Transaction): Transaction{
        // Title
        print(Constants.ASK_CHANGE_TITLE.message)
        check = readlnOrNull()?.trim()
        // Simplified condition - only proceed if input is exactly "y" or "Y"
        if (check.equals("y", ignoreCase = true)) {
            print(Constants.ENTER_TITLE.message)
            val title = readlnOrNull()?.trim()?.takeIf { it.isNotEmpty() } ?: run {
                Colors().printRedColorText(Constants.INVALID_TITLE.message)
                currentTransaction.title
            }
            return currentTransaction.copy(
                title = title,
                date = Date()
            )
        }
        else return currentTransaction
    }

    private fun editTransactionAmount(currentTransaction: Transaction): Transaction{
        print(Constants.ASK_CHANGE_AMOUNT.message)
        check = readlnOrNull()?.trim()

        if (check.equals("y", ignoreCase = true)) {
            print(Constants.ENTER_AMOUNT.message)
            val amount = readlnOrNull()?.toDoubleOrNull() ?: run {
                Colors().printRedColorText(Constants.INVALID_AMOUNT.message)
                currentTransaction.amount
            }
            return currentTransaction.copy(
                amount = amount,
                date = Date()
            )
        }else return currentTransaction
    }

    private fun editTransactionType(currentTransaction: Transaction): Transaction{
        print(Constants.ASK_CHANGE_TYPE.message)
        check = readlnOrNull()?.trim()

        if (check.equals("y", ignoreCase = true)) {
            val type = getValidTransactionType()
            return currentTransaction.copy(
                transactionType = type,
                date = Date()
            )
        }else return currentTransaction
    }

    private fun editTransactionCategory(currentTransaction: Transaction): Transaction{
        print(Constants.ASK_CHANGE_CATEGORY.message)
        check = readlnOrNull()?.trim()

        if (check.equals("y", ignoreCase = true)) {
            val category = getValidCategory()
            return currentTransaction.copy(
                category = category,
                date = Date()
            )
        }else return currentTransaction
    }

    private fun checkYear(year: Int): Boolean {
        val currentYear = LocalDate.now().year
        return year in 1..currentYear
    }

    private fun viewTransactionsInMonth(month: Int, year: Int) {
        val summary = manager.getMonthlySummery(month, year)

        if (summary.isEmpty()) {
            Colors().printRedColorText("${Constants.MONTHLY_SUMMARY_NOT_FOUND.message} $month/$year.")
            return
        }

        summary.forEachIndexed { index, transaction ->
            Colors().printPurpleColorText(
                "${index + 1} -> ${Constants.TITLE.message}: ${transaction.title}, " +
                        "${Constants.AMOUNT.message}: ${transaction.amount}, " +
                        "${Constants.TYPE.message}: ${transaction.transactionType.name
                            .lowercase().replaceFirstChar { it.uppercase() }}, " +
                        "${Constants.CATEGORY.message}: ${transaction.category.name.lowercase()
                            .replaceFirstChar { it.uppercase() }}, " +
                        "${Constants.DATE.message}: ${transaction.date}"
            )
        }
    }

}

