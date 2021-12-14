package com.powilliam.fluffychainsaw.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.powilliam.fluffychainsaw.data.usecases.DeleteAllVariableExpensesUseCase
import com.powilliam.fluffychainsaw.data.usecases.GetMonthEndingFromSettingsDataStoreUseCase
import com.powilliam.fluffychainsaw.data.usecases.UpdateMonthEndingFromSettingsDataStoreUseCase
import com.powilliam.fluffychainsaw.ui.utils.daysUntil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

@HiltWorker
class MonthEndingWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val deleteAllVariableExpensesUseCase: DeleteAllVariableExpensesUseCase,
    private val getMonthEndingFromSettingsDataStoreUseCase: GetMonthEndingFromSettingsDataStoreUseCase,
    private val updateMonthEndingFromSettingsDataStoreUseCase: UpdateMonthEndingFromSettingsDataStoreUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            val monthEndingInUtcMilliseconds =
                getMonthEndingFromSettingsDataStoreUseCase.execute().first()
            val daysUntilNow = daysUntil(monthEndingInUtcMilliseconds)

            when {
                isMonthEndingToday(daysUntilNow) -> {
                    deleteAllVariableExpensesUseCase.execute()
                    updateMonthEndingFromSettingsDataStoreUseCase.execute(0)
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

    companion object {
        const val periodicWorkerName = "PeriodicMonthEndingWorker"
        private val constraints = Constraints.Builder().setRequiresBatteryNotLow(true).build()
        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<MonthEndingWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.SECONDS)
                .build()
    }
}