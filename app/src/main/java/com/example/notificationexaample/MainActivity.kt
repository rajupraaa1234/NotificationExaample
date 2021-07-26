package com.example.notificationexaample

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var button: Button? = null
    var touch: Button? = null
    var reply: Button? = null
    var notificationBuildClass: NotificationBuildClass? = null
    var notificationwithTouch: NotificationwithTouch? = null
    var replyNotifications: ReplyNotifications? = null
    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.simplenotification)
        touch = findViewById(R.id.notificationwithtouch)
        reply = findViewById(R.id.replyNotifications)
        notificationBuildClass = NotificationBuildClass(this)
        notificationwithTouch = NotificationwithTouch(this)
        replyNotifications = ReplyNotifications(this)
        with(touch) { this?.setOnClickListener(View.OnClickListener { TapNotifications() }) }
        with(button) { this?.setOnClickListener(View.OnClickListener { simpleNotification() }) }
        with(reply) { this?.setOnClickListener(View.OnClickListener { replyNotification() }) }
    }

    fun simpleNotification() {
        notificationBuildClass!!.sendHighPriorityNotification(
            "My Notification",
            "This is Raju kumar"
        )
    }

    fun TapNotifications() {
        notificationwithTouch!!.sendHighPriorityNotification(
            "My Pending Notifications",
            "This is android cource",
            MainActivity::class.java
        )
    }

    fun replyNotification() {
        replyNotifications!!.sendHighPriorityNotification(
            "ReplyNotifications",
            "This is awsome Reply Notification,",
            MainActivity::class.java
        )
    }
}