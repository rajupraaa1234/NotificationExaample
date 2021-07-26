package com.example.notificationexaample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import java.util.*

class ReplyNotifications @RequiresApi(api = Build.VERSION_CODES.O) constructor(base: Context?) :
    ContextWrapper(base) {
    private val CHANNEL_NAME = "High priority channel"
    private val CHANNEL_ID = "mynotification $CHANNEL_NAME"
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createChannels() {
        val notificationChannel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.description = "this is the description of the channel."
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(notificationChannel)
    }

    fun sendHighPriorityNotification(title: String?, body: String?, ActivityName: Class<*>?) {
        val intent = Intent(this, ActivityName)
        val pendingIntent =
            PendingIntent.getActivity(this, 267, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_baseline_notifications_off_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true) // use for after touch notification it is remove automatically
            .setContentIntent(pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).setLabel("reply").build()
            val replyintent = Intent(this, Reply_Notification_Text::class.java)
            replyintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val replypendingintent =
                PendingIntent.getActivity(this, 0, replyintent, PendingIntent.FLAG_ONE_SHOT)

            // Now action should be define
            val action = NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_notifications_off_24,
                "Reply",
                replypendingintent
            )
                .addRemoteInput(remoteInput).build()
            notification.addAction(action)
        }
        NotificationManagerCompat.from(this).notify(Random().nextInt(), notification.build())
    }

    companion object {
        const val KEY_TEXT_REPLY = "key_text_reply"
    }

    init {
        createChannels()
    }
}