package com.dev.testapp.data.repository

import com.dev.testapp.data.enums.EActionType

interface ISettingsRepository {
    suspend fun sendSettings()
    fun checkIsEnabled(type: EActionType): Boolean
}