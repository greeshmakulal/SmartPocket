package com.greeshma.smartpocket.data.mapper

import com.greeshma.smartpocket.data.local.PocketItemEntity
import com.greeshma.smartpocket.domain.model.PocketItem


    fun PocketItemEntity.toDomain(): PocketItem {
        return PocketItem(
            id = id,
            name = name,
            category = category,
            purchaseDate = purchaseDate,
            expiryDate = expiryDate,
            imageUri = imageUri,
            createdAt = createdAt,
            notes = notes
        )
    }
    fun PocketItem.toEntity(): PocketItemEntity {
        return PocketItemEntity(
            id = id,
            name = name,
            category = category,
            purchaseDate = purchaseDate,
            expiryDate = expiryDate,
            imageUri = imageUri,
            notes = notes,
            createdAt = createdAt
        )
    }
