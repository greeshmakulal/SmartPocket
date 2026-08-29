package com.greeshma.smartpocket.domain.repository


import com.greeshma.smartpocket.domain.model.PocketItem
import kotlinx.coroutines.flow.Flow

interface PocketRepository {
    suspend fun  addPocketItem(item: PocketItem):Long
    suspend fun updatePocket(item: PocketItem)
    suspend fun deletePocket(item: PocketItem)
    fun getAllSmartPockets(): Flow<List<PocketItem>>
}