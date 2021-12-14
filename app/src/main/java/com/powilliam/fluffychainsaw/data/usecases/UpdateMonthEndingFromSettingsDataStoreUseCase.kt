package com.powilliam.fluffychainsaw.data.usecases

import android.content.Context
import com.powilliam.fluffychainsaw.data.databases.settingsDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UpdateMonthEndingFromSettingsDataStoreUseCase {
    suspend fun execute(monthEndingInUtcMilliseconds: Long)
}

class UpdateMonthEndingFromSettingsDataStoreUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UpdateMonthEndingFromSettingsDataStoreUseCase {
    override suspend fun execute(monthEndingInUtcMilliseconds: Long) {
        withContext(Dispatchers.IO) {
            context.settingsDataStore.updateData { settings ->
                settings.toBuilder()
                    .setMonthEndingInUtcMilliseconds(monthEndingInUtcMilliseconds)
                    .build()
            }
        }
    }
}