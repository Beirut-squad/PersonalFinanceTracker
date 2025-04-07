package core

import data.models.BalanceReport
import data.models.MonthlySummary

interface Reporter {
    fun provideMonthlySummary(): MonthlySummary

    fun provideBalanceReport(): BalanceReport
}