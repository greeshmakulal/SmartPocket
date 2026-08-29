package com.greeshma.smartpocket.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Beenhere
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecentScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val items by viewModel.recentItems.collectAsStateWithLifecycle()

    ViewAll(
        title = "Items Added",
        items = items,
        onBackClick = onBackClick
    ) { item ->

        RecentItem(
            item = item,
            icon = Icons.Default.Beenhere
        )
    }
}

