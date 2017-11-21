package site.tsun.kotlinexamples.common.notification

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat

class NotificationUtil(val mContext: Context, val notificationId: Int) {

    var notificationManager: NotificationManager
    var cBuilder: NotificationCompat.Builder

    init {
        notificationManager = mContext.getSystemService(Activity.NOTIFICATION_SERVICE) as NotificationManager
        cBuilder = NotificationCompat.Builder(mContext)
    }

    private fun initCompatBuilder(pendingIntent: PendingIntent, smallIcon: Int,
                                  ticker: String, title: String, content: String,
                                  enableSound: Boolean, enableVibrate: Boolean, enableLights: Boolean,
                                  isBigText: Boolean = false) {
        cBuilder.setContentIntent(pendingIntent)
        cBuilder.setSmallIcon(smallIcon)
        cBuilder.setTicker(ticker)
        cBuilder.setContentTitle(title)
        cBuilder.setContentText(content)
        if (isBigText) {
            cBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(content))
        }
        cBuilder.setWhen(System.currentTimeMillis())
        // notification will be removed when AutoCancel is true
        cBuilder.setAutoCancel(true)
        //  notifications do not have an 'X' close button, and are not affected by the "Clear all" button
        // cBuilder.setOngoing(true)
        cBuilder.priority = NotificationCompat.PRIORITY_HIGH

        var defaults = 0

        if (enableSound) {
            defaults = defaults or Notification.DEFAULT_SOUND
        }
        if (enableVibrate) {
            defaults = defaults or Notification.DEFAULT_VIBRATE
        }
        if (enableLights) {
            defaults = defaults or Notification.DEFAULT_LIGHTS
        }
        cBuilder.setDefaults(defaults)
    }

    /**
     * send normal single line notification
     */
    fun sendSingleLineNotification(pendingIntent: PendingIntent, smallIcon: Int, ticker: String, title: String, content: String,
                                            enableSound: Boolean, enableVibrate: Boolean, enableLights: Boolean) {
        initCompatBuilder(pendingIntent, smallIcon, ticker, title, content, enableSound, enableVibrate, enableLights)
        sendNotification()
    }

    /**
     * send normal multiple lines notification
     * only be supported in higher android version
     */
    fun sendMultipleLinesNotification(pendingIntent: PendingIntent, smallIcon: Int, ticker: String, title: String, content: String,
                                          enableSound: Boolean, enableVibrate: Boolean, enableLights: Boolean) {
        initCompatBuilder(pendingIntent, smallIcon, ticker, title, content, enableSound, enableVibrate, enableLights, true)
        sendNotification()
    }

    /**
     * send normal multiple lines notification
     * only be supported in higher android version
     */
    fun sendInboxNotification(pendingIntent: PendingIntent, smallIcon: Int, ticker: String, title: String, content: String,
                                             enableSound: Boolean, enableVibrate: Boolean, enableLights: Boolean, largeIcon: Int, messageList: ArrayList<String>) {
        initCompatBuilder(pendingIntent, smallIcon, ticker, title, content, enableSound, enableVibrate, enableLights, true)
        var bitmap = BitmapFactory.decodeResource(mContext.resources, largeIcon)
        cBuilder.setLargeIcon(bitmap)
        var inboxStyle = NotificationCompat.InboxStyle()
        for (message in messageList) {
            inboxStyle.addLine(message)
        }
        inboxStyle.setSummaryText("[" + messageList.size + "]" + title)
        cBuilder.setStyle(inboxStyle)
        sendNotification()
    }

    /**
     * send normal heads-up notification
     */
    fun sendHeadsUpNotification(pendingIntent: PendingIntent, pushPendingIntent: PendingIntent, smallIcon: Int, ticker: String, title: String, content: String,
                                          enableSound: Boolean, enableVibrate: Boolean, enableLights: Boolean) {
        initCompatBuilder(pendingIntent, smallIcon, ticker, title, content, enableSound, enableVibrate, enableLights)

        sendNotification()
    }

    private fun sendNotification() {
        var notification = cBuilder.build();
        // send notification
        notificationManager.notify(notificationId, notification);
    }
}