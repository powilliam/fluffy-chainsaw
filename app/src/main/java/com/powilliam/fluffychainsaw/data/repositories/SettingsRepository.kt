package com.powilliam.fluffychainsaw.data.repositories

import com.powilliam.fluffychainsaw.Settings
import com.powilliam.fluffychainsaw.data.datasources.SettingsLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SettingsRepository {
    suspend fun getSettings(): Flow<Settings>
    suspend fun updateSettings(monthEndingInUtcMilliseconds: Long)
}

class SettingsRepositoryImpl @Inject constructor(
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {
    override suspend fun getSettings(): Flow<Settings> = settingsLocalDataSource.getSettings()

    override suspend fun updateSettings(monthEndingInUtcMilliseconds: Long) =
        settingsLocalDataSource.updateSettings(monthEndingInUtcMilliseconds)
}