package com.greeshma.smartpocket.domain.model


data class PocketItem(

    val id: Long = 0,

    val name: String,

    val category: String,

    val purchaseDate: Long?,

    val expiryDate: Long?,
    val imageUri: String?,

    val notes: String,

    val createdAt: Long
)