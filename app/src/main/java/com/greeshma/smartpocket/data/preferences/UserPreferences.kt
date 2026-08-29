package com.greeshma.smartpocket.data.preferences

data class UserPreferences(
    val ownerName: String = "",
    val email: String = "",
    val phone: String = "",
    val biometricEnabled: Boolean = false,
    val setupCompleted: Boolean = false
)
