package com.greeshma.smartpocket.domain.usecase

import com.greeshma.smartpocket.domain.model.PocketItem
import com.greeshma.smartpocket.domain.repository.PocketRepository
import com.greeshma.smartpocket.domain.validation.ValidationResult
import com.greeshma.smartpocket.presentation.add.AddUiState
import javax.inject.Inject


class ValidateAddItem @Inject constructor() {

    operator fun invoke(
        state: AddUiState
    ): ValidationResult {

        if (state.documentName.isBlank()) {
            return ValidationResult.Error(
                nameError = "Name is required"
            )
        }

        if (state.category.isBlank()) {
            return ValidationResult.Error(
                categoryError = "Category is required"
            )
        }

        if (state.purchaseDate == null) {
            return ValidationResult.Error(
                purchaseDateError = "Purchase Date is required"
            )
        }

        if (state.expiryDate == null) {
            return ValidationResult.Error(
                expiryDateError = "Expiry Date is required"
            )
        }

        return ValidationResult.Success
    }
}