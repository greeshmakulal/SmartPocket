package com.greeshma.smartpocket.presentation.add

import android.net.Uri

data class AddUiState(
    var documentName: String = "",
    var category:String ="",
    var purchaseDate:Long?=null,
    var expiryDate:Long?=null,
    var notes: String="",
    val documentNameError: String? = null,
    val imageUri: Uri? = null,
    val categoryError: String? = null,
    val purchaseDateError: String? = null,
    val expiryDateError: String? = null,
    val imageUriError: String? = null
    )
