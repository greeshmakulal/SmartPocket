package com.greeshma.smartpocket.data.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object ExpiryNotificationScheduler {

    fun schedule(context: Context) {

        val workRequest =
            PeriodicWorkRequestBuilder<ExpiryNotificationWorker>(
                1,
                TimeUnit.DAYS
            )
                .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "expiry_notification_work",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }
}