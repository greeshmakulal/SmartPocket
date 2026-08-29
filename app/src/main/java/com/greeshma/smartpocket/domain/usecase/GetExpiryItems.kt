package com.greeshma.smartpocket.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.greeshma.smartpocket.domain.model.PocketItem
import com.greeshma.smartpocket.domain.repository.PocketRepository
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class GetExpiryItems @Inject constructor() {

    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(items: List<PocketItem>): List<PocketItem> {

        val today = LocalDate.now()
        val fiveDaysLater = today.plusDays(5)

        return items.filter { item ->

            item.expiryDate?.let { expiryMillis ->

                val expiryDate = Instant.ofEpochMilli(expiryMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()

                expiryDate in today..fiveDaysLater

            } ?: false
        }
    }

}