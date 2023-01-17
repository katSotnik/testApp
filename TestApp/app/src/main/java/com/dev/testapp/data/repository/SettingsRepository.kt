package com.dev.testapp.data.repository

import com.dev.testapp.data.enums.EActionType
import com.dev.testapp.data.models.SettingsModel
import com.dev.testapp.data.network.NetworkResult
import com.dev.testapp.data.requesters.SettingsRequester
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsRequester: SettingsRequester
) : ISettingsRepository {

    override suspend fun sendSettings() {
        val response = settingsRequester.sendRequest()
    }

    override fun checkIsEnabled(type: EActionType): Boolean {
        //todo next logic
        return true
    }

    private fun saveSettings(data: List<SettingsModel>?) {

    }


}