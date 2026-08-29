package com.greeshma.smartpocket.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greeshma.smartpocket.data.preferences.UserPreferences
import com.greeshma.smartpocket.data.preferences.UserPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor( val preferences: UserPreferencesDataStore): ViewModel() {

    val userPreferences: StateFlow<UserPreferences> =
        preferences.userPreferences
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UserPreferences()
            )

    fun setBiometricEnabled(enabled: Boolean) {

        viewModelScope.launch {
            preferences.setBiometricEnabled(enabled)
        }
    }
}