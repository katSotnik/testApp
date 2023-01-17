package com.dev.testapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.dev.testapp.Constants.PUSH_NOTIFICATION_MODEL
import com.dev.testapp.MainActivity
import com.dev.testapp.R
import com.dev.testapp.data.models.NotificationModel
import kotlin.random.Random

class CallReceiver : BroadcastReceiver() {

    companion object {
        const val CHANNEL_ID = "TEST_NOTIFICATION_CHANNEL"
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
    }

    fun showNotification(context: Context?, message: String) {
        var notificationId = 567
        notificationId += Random(notificationId).nextInt(1, 30)
        context?.let { c ->
            val intent = Intent(c, MainActivity::class.java)
            intent.putExtra(PUSH_NOTIFICATION_MODEL, NotificationModel(notificationId)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val bundle = Bundle()
            bundle.putParcelable(PUSH_NOTIFICATION_MODEL, NotificationModel(notificationId))
            val pendingIntent = NavDeepLinkBuilder(c)
                .setComponentName(MainActivity::class.java)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.mainFragment)
                .setArguments(bundle)
                .createPendingIntent()
            val notificationBuilder =
                NotificationCompat.Builder(c.applicationContext, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_alarm)
                    .setContentTitle(c.getString(R.string.notification))
                    .setContentText(message)
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(message)
                    )
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager =
                c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val appName = c.getString(R.string.app_name)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    appName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = appName
                }
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(notificationId, notificationBuilder.build())
        }
    }
}