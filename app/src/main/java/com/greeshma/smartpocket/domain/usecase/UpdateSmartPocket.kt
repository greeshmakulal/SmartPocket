package com.greeshma.smartpocket.domain.usecase

import com.greeshma.smartpocket.domain.model.PocketItem
import com.greeshma.smartpocket.domain.repository.PocketRepository
import javax.inject.Inject

class UpdateSmartPocket @Inject constructor( private val repository: PocketRepository) {

    suspend operator  fun invoke(item: PocketItem)
    {
        repository.updatePocket(item = item)
    }
}