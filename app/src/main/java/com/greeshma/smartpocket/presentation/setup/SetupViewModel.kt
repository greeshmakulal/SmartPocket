package com.greeshma.smartpocket.presentation.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greeshma.smartpocket.data.preferences.UserPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val preferencesDataStore: UserPreferencesDataStore
) : ViewModel() {

    fun saveSetup(
        ownerName: String,
        email: String,
        phone: String,
        biometricEnabled: Boolean,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {

            preferencesDataStore.saveUserDetails(
                ownerName = ownerName,
                email = email,
                phone = phone,
                biometricEnabled = biometricEnabled
            )

            onComplete()
        }
    }
}