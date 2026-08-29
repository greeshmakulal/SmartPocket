package com.greeshma.smartpocket.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smart_pocket")
data class PocketItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val category: String,
    val purchaseDate: Long?,
    val expiryDate: Long?,
    val imageUri: String?,
    val notes: String,
    val createdAt: Long = System.currentTimeMillis()
)