package site.tsun.kotlinexamples.common.fcm

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_fcm_demo.*
import site.tsun.kotlinexamples.R


class FcmDemoActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcm_demo)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW))
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                val value = intent.extras!!.get(key)
                Log.d(TAG, "Key: $key Value: $value")
            }
        }
        // [END handle_data_extras]


        subscribeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("news")
                // [END subscribe_topics]

                // Log and toast
                val msg = getString(R.string.msg_subscribed)
                Log.d(TAG, msg)
                Toast.makeText(this@FcmDemoActivity, msg, Toast.LENGTH_SHORT).show()
            }
        })


        logTokenButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Get token
                val token = FirebaseInstanceId.getInstance().token

                // Log and toast
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, msg)
                Toast.makeText(this@FcmDemoActivity, msg, Toast.LENGTH_SHORT).show()
            }
        })
    }
}