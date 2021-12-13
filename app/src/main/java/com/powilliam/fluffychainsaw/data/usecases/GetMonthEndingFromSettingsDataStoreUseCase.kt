package com.powilliam.fluffychainsaw.data.usecases

import com.powilliam.fluffychainsaw.Settings
import javax.inject.Inject

interface GetSettingsUseCase {
    suspend fun execute(): Settings
}

class GetSettingsUseCaseImpl @Inject constructor() : GetSettingsUseCase {
    override suspend fun execute(): Settings {
        TODO("Not yet implemented")
    }
}