package com.dev.testapp.data.requesters

import com.dev.testapp.data.models.SettingsModel
import com.dev.testapp.data.network.NetworkResult
import com.dev.testapp.data.network.RestApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRequester @Inject constructor(
    private val restApiService: RestApiService
) {
    suspend fun sendRequest(): NetworkResult<List<SettingsModel>?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = restApiService.getSettings()
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body())
                } else NetworkResult.Error()
            } catch (ex: Exception) {
                NetworkResult.Error()
            }
        }
    }
}