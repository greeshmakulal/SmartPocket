package com.greeshma.smartpocket.presentation.add

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.greeshma.smartpocket.domain.model.PocketItem
import com.greeshma.smartpocket.domain.usecase.AddSmartPocketItem
import com.greeshma.smartpocket.domain.usecase.ValidateAddItem
import com.greeshma.smartpocket.domain.validation.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URI
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(val validationUseCase: ValidateAddItem,
                                       private val addPocketItemUseCase: AddSmartPocketItem
): ViewModel() {
    private val _uiState = MutableStateFlow(AddUiState())
    val uiState: StateFlow<AddUiState> = _uiState.asStateFlow()
    private val _events = MutableSharedFlow<AddEvent>()
    val events = _events.asSharedFlow()
    fun onNameChanged(name: String) {
        _uiState.update {
            it.copy(documentName = name)
        }
    }

    fun onCategoryChanged(category: String) {
        _uiState.update {
            it.copy(category = category)
        }
    }

    fun onPurchaseDateChanged(date: Long?) {
        _uiState.update {
            it.copy(purchaseDate = date)
        }
    }

    fun onExpiryDateChanged(date: Long?) {
        _uiState.update {
            it.copy(expiryDate = date)
        }
    }

    fun onNotesChanged(notes: String) {
        _uiState.update {
            it.copy(notes = notes)
        }
    }
    fun onUriChanged(uri: Uri) {
        _uiState.update {
            it.copy(imageUri = uri)
        }
    }
    fun onImageSelected(uri: Uri) {

        _uiState.update {
            it.copy(imageUri = uri)
        }

    }

    fun saveItem() = viewModelScope.launch {
        val state = uiState.value

        when(val result = validationUseCase.invoke(state))
        {
            ValidationResult.Success->
            {
                addPocketItemUseCase.invoke(PocketItem(
                    id = 0,
                    name = state.documentName,
                    category = state.category,
                    purchaseDate = state.purchaseDate,
                    expiryDate = state.expiryDate,
                    imageUri = state.imageUri.toString(),
                    notes = state.notes,
                    createdAt = 1L
                ))
                _events.emit(AddEvent.ItemAddedSuccessfully)

            }
           is  ValidationResult.Error->
            {
                _uiState.update { it.copy(
                    documentNameError = result.nameError,
                    purchaseDateError = result.purchaseDateError,
                    expiryDateError = result.expiryDateError,

                    ) }
                _events.emit(AddEvent.Error("Something went wrong!!"))
            }
        }


    }

    fun clearForm() {
        _uiState.value = AddUiState()
    }
}