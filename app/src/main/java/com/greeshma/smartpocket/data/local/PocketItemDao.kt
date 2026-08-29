package com.greeshma.smartpocket.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PocketItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addPocketItem(item: PocketItemEntity):Long

    @Update
    suspend fun updatePocket(itemDao: PocketItemEntity)

    @Delete
    suspend fun deletePocket(itemDao: PocketItemEntity)

    @Query("SELECT * FROM smart_pocket")
    fun getAllSmartPockets(): Flow<List<PocketItemEntity>>
    @Query("SELECT * FROM smart_pocket")
    suspend fun getAllItemsOnce(): List<PocketItemEntity>


}