package ui

enum class Constants(val message: String) {
    MAIN_MENU_TITLE("Finance Tracker"),
    MAIN_MENU_OPTION_1("\t1. Manage Transactions"),
    MAIN_MENU_OPTION_2("\t2. View Monthly Summary"),
    MAIN_MENU_OPTION_3("\t3. View Balance Report"),
    MAIN_MENU_OPTION_4("\t4. Exit"),
    MAIN_MENU_PROMPT("- Choose an option (1, 4): "),
    INVALID_CHOICE("Invalid choice."),
    GOODBYE("Goodbye!"),

    // Transactions Menu
    TRANSACTION_MENU_TITLE("Manage your transactions: "),
    TRANSACTION_MENU_OPTION_1("\t1. Add Transaction"),
    TRANSACTION_MENU_OPTION_2("\t2. Edit Transaction"),
    TRANSACTION_MENU_OPTION_3("\t3. Delete Transaction"),
    TRANSACTION_MENU_OPTION_4("\t4. View Transactions"),
    TRANSACTION_MENU_OPTION_5("\t5. Exit to main menu"),
    TRANSACTION_MENU_PROMPT("- Choose an option (1, 5): "),

    // Add Transaction
    ADD_TRANSACTION_TITLE("Enter the details of your transaction"),
    ENTER_TITLE("\tEnter title: "),
    ENTER_AMOUNT("\tEnter amount: "),
    TRANSACTION_ADDED_SUCCESSFULLY("Transactions Added"),
    TRANSACTION_ADD_FAILED("please try again , there is an error in the data"),

    // Edit Transaction
    CHOOSE_TRANSACTION_TO_EDIT("\tChoose the transaction you want to edit"),
    EDIT_TRANSACTION_TITLE("Edit your transaction"),
    TRANSACTION_EDITED_SUCCESSFULLY("Transaction edited"),

    // Delete Transaction
    CHOOSE_TRANSACTION_TO_DELETE("\tChoose the transaction you want to delete"),
    DELETE_TRANSACTION_TITLE("Delete your transaction"),
    TRANSACTION_DELETED_SUCCESSFULLY("Transaction deleted"),
    TRANSACTION_DELETE_FAILED("No transactions available to delete."),

    // Transaction Type and Category
    CHOOSE_TRANSACTION_TYPE("\tChoose transaction type\n\t\t1: Income\n\t\t2: Expense"),
    TRANSACTION_TYPE_PROMPT("- Choose an option (1, 2): "),
    TRANSACTION_TYPE_INVALID("Invalid input. Please enter 1 or 2."),
    CHOOSE_CATEGORY("\tChoose category"),
    CATEGORY_PROMPT("- Choose an option (a number from 1 to "),
    CATEGORY_INVALID_INPUT("Invalid input. Please enter a number between 1 and "),

    // Edit Fields
    ASK_CHANGE_TITLE("Do you want to change the title? (Enter y if you want or anything else to skip): "),
    ASK_CHANGE_AMOUNT("Do you want to change the amount? (Enter y if you want or anything else to skip): "),
    ASK_CHANGE_TYPE("Do you want to change the type? (Enter y if you want or anything else to skip): "),
    ASK_CHANGE_CATEGORY("Do you want to change the category? (Enter y if you want or anything else to skip): "),
    INVALID_TITLE("Title cannot be empty. Keeping previous title."),
    INVALID_AMOUNT("Invalid amount. Keeping previous amount."),

    // Monthly Summary
    ENTER_MONTH("Enter month (1-12): "),
    ENTER_YEAR("Enter year: "),
    INVALID_MONTH("Invalid month, or empty input."),
    INVALID_YEAR("Invalid year, or empty input."),

    // Balance Report
    SHOW_BALANCE("Show Total Balance report "),
    BALANCE_DETAILS("Balance report is: "),
    INCOME_BALANCE_DETAILS("Income Balance report is: "),
    EXPENSES_BALANCE_DETAILS("Expenses Balance report is: "),

    // General
    NO_TRANSACTIONS("You don't have any transactions."),
    MONTHLY_SUMMARY_NOT_FOUND("No transactions found for"),

    // Fields To Edit
    AMOUNT("Amount"),
    TYPE("Type"),
    CATEGORY("Category"),
    DATE("Date"),
    TITLE("Title"),
    ;

}
