package com.powilliam.fluffychainsaw.data.usecases

import android.content.Context
import com.powilliam.fluffychainsaw.data.databases.settingsDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetMonthEndingFromSettingsDataStoreUseCase {
    suspend fun execute(): Flow<Long>
}

class GetMonthEndingFromSettingsDataStoreUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GetMonthEndingFromSettingsDataStoreUseCase {
    override suspend fun execute(): Flow<Long> {
        return withContext(Dispatchers.IO) {
            context.settingsDataStore.data.map { settings -> settings.monthEndingInUtcMilliseconds }
        }
    }
}