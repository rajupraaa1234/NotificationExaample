package com.example.notificationexaample

import android.app.RemoteInput
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class Reply_Notification_Text : AppCompatActivity() {
    var textView: TextView? = null
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reply__notification__text)
        textView = findViewById(R.id.replytext)
        val replyinput = RemoteInput.getResultsFromIntent(intent)
        if (replyinput != null) {
            val str = replyinput.getCharSequence(ReplyNotifications.KEY_TEXT_REPLY) as String?
            with(textView) {
                this?.setText(str)
            }
        }
    }
}