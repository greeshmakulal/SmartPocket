package com.greeshma.smartpocket.data.preferences


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)

@Singleton
class UserPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private object Keys {
        val OWNER_NAME = stringPreferencesKey("owner_name")
        val EMAIL = stringPreferencesKey("email")
        val PHONE = stringPreferencesKey("phone")
        val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
        val SETUP_COMPLETED = booleanPreferencesKey("setup_completed")
    }

    val userPreferences: Flow<UserPreferences> =
        context.dataStore.data.map { preferences ->

            UserPreferences(
                ownerName = preferences[Keys.OWNER_NAME] ?: "",
                email = preferences[Keys.EMAIL] ?: "",
                phone = preferences[Keys.PHONE] ?: "",
                biometricEnabled = preferences[Keys.BIOMETRIC_ENABLED] ?: false,
                setupCompleted = preferences[Keys.SETUP_COMPLETED] ?: false
            )
        }

    suspend fun saveUserDetails(
        ownerName: String,
        email: String,
        phone: String,
        biometricEnabled: Boolean
    ) {
        context.dataStore.edit { preferences ->

            preferences[Keys.OWNER_NAME] = ownerName
            preferences[Keys.EMAIL] = email
            preferences[Keys.PHONE] = phone
            preferences[Keys.BIOMETRIC_ENABLED] = biometricEnabled
            preferences[Keys.SETUP_COMPLETED] = true
        }
    }

    suspend fun setBiometricEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.BIOMETRIC_ENABLED] = enabled
        }
    }
}