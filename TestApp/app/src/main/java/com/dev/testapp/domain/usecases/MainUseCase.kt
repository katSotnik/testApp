package com.dev.testapp.domain.usecases

import com.dev.testapp.data.enums.EActionType
import com.dev.testapp.data.repository.ISettingsRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {
    fun checkIsEnabled(type: EActionType): Boolean {
        return settingsRepository.checkIsEnabled(type)
    }

    suspend fun getSettings() {
        return settingsRepository.sendSettings()
    }


}