package com.powilliam.fluffychainsaw.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.powilliam.fluffychainsaw.data.repositories.ExpensesRepository
import com.powilliam.fluffychainsaw.data.repositories.SettingsRepository
import com.powilliam.fluffychainsaw.ui.utils.daysUntil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class MonthEndingWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val expensesRepository: ExpensesRepository,
    private val settingsRepository: SettingsRepository
) : CoroutineWorker(context, params) {
    companion object {
        const val workerName = "MonthEndingWorker"
        const val workerTag = "MonthEndingWorkerTag"
    }

    override suspend fun doWork(): Result {
        return try {
            val settings = settingsRepository.getSettings().first()
            val daysUntilNow = daysUntil(settings.monthEndingInUtcMilliseconds)

            when {
                isMonthEndingToday(daysUntilNow) -> {
                    expensesRepository.deleteAllVariableExpenses()
                    settingsRepository.updateSettings(monthEndingInUtcMilliseconds = 0)
                }
                else -> {}
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private fun isMonthEndingToday(daysUntilNow: Int): Boolean {
        return daysUntilNow == 0
    }
}