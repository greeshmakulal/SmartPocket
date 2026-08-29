package com.greeshma.smartpocket.domain.repository

import androidx.room.Insert
import com.greeshma.smartpocket.data.local.PocketItemDao
import com.greeshma.smartpocket.data.mapper.toDomain
import com.greeshma.smartpocket.data.mapper.toEntity
import com.greeshma.smartpocket.domain.model.PocketItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PocketRepositoryImpl @Inject constructor(private val dao: PocketItemDao): PocketRepository {
    override suspend fun addPocketItem(item: PocketItem): Long {
      return dao.addPocketItem(item.toEntity())
    }

    override suspend fun updatePocket(itemDao: PocketItem) {
        dao.updatePocket(itemDao.toEntity())
    }

    override suspend fun deletePocket(itemDao: PocketItem) {
        dao.deletePocket(itemDao.toEntity())
    }

    override fun getAllSmartPockets(): Flow<List<PocketItem>> {
        return dao.getAllSmartPockets().map { entityList ->

            entityList.map { entity ->
                entity.toDomain()
            }

        }
    }
}