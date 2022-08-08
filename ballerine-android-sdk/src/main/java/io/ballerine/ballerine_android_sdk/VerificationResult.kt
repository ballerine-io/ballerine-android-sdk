package io.ballerine.ballerine_android_sdk

import androidx.annotation.Keep

@Keep
data class VerificationResult(
    val isSync: Boolean,
    val status: String?,
    val idvResult: String?,
    val code: String?,
)
