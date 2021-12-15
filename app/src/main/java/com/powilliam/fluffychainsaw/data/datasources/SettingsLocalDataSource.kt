package com.powilliam.fluffychainsaw.data.datasources

import android.content.Context
import com.powilliam.fluffychainsaw.Settings
import com.powilliam.fluffychainsaw.data.persistence.settingsDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SettingsLocalDataSource {
    suspend fun getSettings(): Flow<Settings>
    suspend fun updateSettings(monthEndingInUtcMilliseconds: Long)
}

class SettingsLocalDataSourceImpl @Inject constructor(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SettingsLocalDataSource {
    override suspend fun getSettings(): Flow<Settings> {
        return withContext(ioDispatcher) {
            context.settingsDataStore.data
        }
    }

    override suspend fun updateSettings(monthEndingInUtcMilliseconds: Long) {
        withContext(ioDispatcher) {
            context.settingsDataStore.updateData { settings ->
                settings.toBuilder()
                    .setMonthEndingInUtcMilliseconds(monthEndingInUtcMilliseconds)
                    .build()
            }
        }
    }
}