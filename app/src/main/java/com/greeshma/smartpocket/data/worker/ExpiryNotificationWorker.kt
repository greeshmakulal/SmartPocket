package com.greeshma.smartpocket.data.worker

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.greeshma.smartpocket.data.local.PocketItemDao
import com.greeshma.smartpocket.data.mapper.toDomain
import com.greeshma.smartpocket.domain.usecase.GetExpiryItems
import com.greeshma.smartpocket.presentation.utils.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@HiltWorker
class ExpiryNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val dao: PocketItemDao,
    private val getExpiryItems: GetExpiryItems
) : CoroutineWorker(context, workerParams) {
    @androidx.annotation.RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS)
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        // Check Room

        val allItems = dao.getAllItemsOnce()
        val items = allItems.map { it.toDomain() }
        val expiringItems = getExpiryItems(items)

        expiringItems.forEach  { item ->

            val expiryDate = Instant.ofEpochMilli(
                item.expiryDate!!
            )
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            val today = LocalDate.now()

            val daysRemaining = ChronoUnit.DAYS.between(
                today,
                expiryDate
            )

            NotificationHelper.showExpiryNotification(
                context = applicationContext,
                itemName = item.name,
                daysRemaining = daysRemaining,
                notificationId = item.id.toInt()
            )
        }
        return Result.success()
    }
}