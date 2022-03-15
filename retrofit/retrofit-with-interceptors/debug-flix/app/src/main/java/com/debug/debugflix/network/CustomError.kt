package com.debug.debugflix.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomError (
    @SerialName("custom_code") val code: Int,
    @SerialName("custom_message") val message: String,
)