package com.greeshma.smartpocket.presentation.add

sealed interface AddEvent {

    data object ItemAddedSuccessfully : AddEvent

    data class Error(
        val message: String
    ) : AddEvent
}