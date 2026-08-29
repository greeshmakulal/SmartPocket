package com.greeshma.smartpocket.domain.validation

sealed class ValidationResult {

    data object Success :ValidationResult()
    data class Error(

        val nameError: String? = null,
        val categoryError: String? = null,
        val purchaseDateError: String? = null,
        val expiryDateError: String? = null

    ) : ValidationResult()

}
