package com.greeshma.smartpocket.presentation.utils


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.greeshma.smartpocket.MainActivity
import com.greeshma.smartpocket.R

object NotificationHelper {

    const val CHANNEL_ID = "expiry_reminders"

    fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Expiry Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for items approaching expiry"
            }

            val notificationManager =
                context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showExpiryNotification(
        context: Context,
        itemName: String,
        daysRemaining: Long,
        notificationId: Int
    ) {

        val message = when (daysRemaining) {
            0L -> "$itemName expires today"
            1L -> "$itemName expires tomorrow"
            else -> "$itemName expires in $daysRemaining days"
        }

        val intent = Intent(context, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(
            context,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.outline_notifications_24)
            .setContentTitle("Expiry Reminder")
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(message)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat
            .from(context)
            .notify(notificationId, notification)
    }
}