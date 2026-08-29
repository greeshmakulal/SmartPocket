package com.greeshma.smartpocket.presentation.home

import com.greeshma.smartpocket.domain.model.PocketItem

data class HomeUiState(
    var isLoading: Boolean = false,
    var items:List<PocketItem> = emptyList(),
    var error: String? = null
    )
