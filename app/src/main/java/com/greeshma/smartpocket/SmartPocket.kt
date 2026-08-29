package com.greeshma.smartpocket

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.greeshma.smartpocket.data.worker.ExpiryNotificationScheduler
import com.greeshma.smartpocket.presentation.utils.NotificationHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
@HiltAndroidApp
class SmartPocket : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        NotificationHelper.createNotificationChannel(this)

         ExpiryNotificationScheduler.schedule(this)
    }
}