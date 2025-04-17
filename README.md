# Personal Finance Tracker

## Project Overview
This Personal Finance Tracker is a console-based (CLI) application developed as part of The Chance program. The application allows users to track their personal finances by managing income and expense transactions, categorizing them, and generating financial reports.

## Features
- **Transaction Management**: Add, view, edit, and delete financial transactions (both income and expenses)
- **Categorization**: Associate each transaction with predefined categories (Food, Rent, Salary, etc.)
- **Financial Reporting**: Generate monthly summary and balance reports based on transaction dates
- **Data Persistence**: CSV file-based storage for persistent transaction data

## Technical Specifications
- **Architecture**: Built with Object-Oriented Programming (OOP) principles
- **Development Workflow**: Implemented using Git Flow with feature branches and meaningful commit messages
- **Testing**: Logic-heavy functionalities include check functions for unit testing

## Project Structure
```
personal-finance-tracker/
├── src/
│   ├── models/            # Data models like Transaction, Category
│   ├── datasources/       # Data storage implementations and interfaces
│   ├── core/              # Core business logic and services
│   ├── utils/             # Helper utilities
│   ├── test/              # Unit tests
│   └── ui/                # User interface components
└── README.md
```

## Usage
The CLI interface provides the following options:


Main Menu:
1. Manage Transactions
2. View Monthly Summary
3. View Balance Report
4. Exit
   
When option 1 is selected, the following sub-menu appears:

Manage your transactions: 
1. Add Transaction
2. Edit Transaction
3. Delete Transaction
4. View Transactions
5. Exit to main menu

## Development

### Git Flow
We follow the Git Flow branching model:
- `main`: Production-ready code
- `develop`: Latest development changes
- `feature/*`: New features being developed
- `fix/*`: Bug fixes

## Contributors
This project was developed collaboratively by the Beirut Squad as part of The Chance program.

<a href="https://github.com/Beirut-squad/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Beirut-squad/PersonalFinanceTracker" />
</a>

