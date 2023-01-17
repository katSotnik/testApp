package com.dev.testapp.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SettingsModel(
    val type: String,
    val enabled: Boolean,
    val priority: Int,
    @Json(name = "valid_days") val validDays: List<Int>,
    @Json(name = "cool_down") val coolDown: Long?,
)