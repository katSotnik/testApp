package com.dev.testapp.data.network

import com.dev.testapp.data.models.SettingsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApiService {
    @Headers("Content-Type: application/json")
    @GET("androidexam/butto_to_action_config.json")
    suspend fun getSettings(): Response<List<SettingsModel>>
}