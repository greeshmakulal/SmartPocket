package com.greeshma.smartpocket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PocketItemEntity::class], version = 2, exportSchema = false)
abstract class PocketDataBase: RoomDatabase() {

    abstract fun getPocketDao(): PocketItemDao
}