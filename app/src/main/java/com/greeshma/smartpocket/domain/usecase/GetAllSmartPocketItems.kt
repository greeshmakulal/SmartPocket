package com.greeshma.smartpocket.domain.usecase

import com.greeshma.smartpocket.domain.model.PocketItem
import com.greeshma.smartpocket.domain.repository.PocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSmartPocketItems @Inject constructor(private val repository: PocketRepository) {

    operator fun invoke(): Flow<List<PocketItem>>
    {
        return repository.getAllSmartPockets()
    }
}