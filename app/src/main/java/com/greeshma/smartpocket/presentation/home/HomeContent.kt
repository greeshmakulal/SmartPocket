package com.greeshma.smartpocket.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greeshma.smartpocket.data.preferences.UserPreferences
import com.greeshma.smartpocket.domain.model.PocketItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(innerPadding: PaddingValues, viewModel: HomeViewModel = hiltViewModel(),  onViewAllExpiry: () -> Unit,
                onViewAllRecent: () -> Unit){
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    val expiringItems by viewModel.expiringItems.collectAsStateWithLifecycle()
    val recentItmes by viewModel.recentItems.collectAsStateWithLifecycle()
    val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle(initialValue = UserPreferences())
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)) {
        item { HomeHeader(
            userPreferences.ownerName,
        )
        {

        }}
            
        item { QuickSummary(totalItems = items.size,expiringItems.size,3) }
        item { if(expiringItems.isNotEmpty())ExpirySection(expiringItems,onViewAllExpiry) }
        item { RecentSection(recentItmes,onViewAllRecent) }
    }
}