package com.greeshma.smartpocket.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greeshma.smartpocket.data.preferences.UserPreferencesDataStore
import com.greeshma.smartpocket.domain.model.PocketItem
import com.greeshma.smartpocket.domain.usecase.GetAllSmartPocketItems
import com.greeshma.smartpocket.domain.usecase.GetExpiryItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(val getAllSmartPocketItems: GetAllSmartPocketItems, val getExpiryItems: GetExpiryItems, val preferences: UserPreferencesDataStore): ViewModel(){
    val _uiState = MutableStateFlow<List<PocketItem>>(emptyList())
    public val uiState: StateFlow<List<PocketItem>> = _uiState
    val _expiryState = MutableStateFlow<List<PocketItem>>(emptyList())
    public val expiryState: StateFlow<List<PocketItem>> = _expiryState
    public  var userPreferences  = preferences.userPreferences
  val recentItems: StateFlow<List<PocketItem>> =
        uiState
            .map { items ->
                items
                    .sortedByDescending { it.createdAt }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    val expiringItems: StateFlow<List<PocketItem>> =
        uiState
            .map { items ->
                getExpiryItems(items)
                    .sortedBy { it.expiryDate }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    init {

        getItems()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getItems()
    {
        viewModelScope.launch {
            getAllSmartPocketItems().collect {
                _uiState.value = it
//                _expiryState.value = getExpiryItems(it)
            }

        }

    }
    fun viewAll() = viewModelScope.launch {
        
    }



}