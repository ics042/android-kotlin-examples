package site.tsun.kotlinexamples.common.notification

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_notification.*
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.util.NotificationUtil


class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    var requestCode: Int = SystemClock.uptimeMillis().toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        initListener()
    }

    private fun initListener() {
        btnSingleLine.setOnClickListener(this)
        btnMultipleLines.setOnClickListener(this)
        btnInbox.setOnClickListener(this)
        btnHeadsUp.setOnClickListener(this)
    }
    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.btnSingleLine -> {
                    sendSingleLine()
                }
                R.id.btnMultipleLines -> {
                    sendMultipleLines()
                }
                R.id.btnInbox -> {
                    sendInBox()
                }
                R.id.btnHeadsUp -> {
                    sendHeadsUp()
                }
            }
        }
    }

    private fun sendMultipleLines() {
        val notificationUtil = NotificationUtil(this, 1)
        var intent = Intent(this, TargetActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        var pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var smallIcon = R.drawable.ic_neteasy
        var ticker = "You have a new notification"
        var title = "Hello World!!!"
        var content = this.getString(R.string.app_bar_kotlin)
        notificationUtil.sendMultipleLinesNotification(pendingIntent, smallIcon, ticker, title, content, true, true, true)
    }

    private fun sendSingleLine() {
        val notificationUtil = NotificationUtil(this, 2)
        var intent = Intent(this, TargetActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        var pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var smallIcon = R.drawable.ic_taobao
        var ticker = "You have a new notification"
        var title = "Hello World!!!"
        var content = "Congratulations! "
        notificationUtil.sendSingleLineNotification(pendingIntent, smallIcon, ticker, title, content, true, true, true)
    }

    private fun sendInBox() {
        val notificationUtil = NotificationUtil(this, 3)
        var intent = Intent(this, TargetActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        var pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var smallIcon = R.drawable.ic_wechat
        var ticker = "You have a new notification"
        var title = "Hello World!!!"
        var content = "Congratulations! "
        val messageList = ArrayList<String>()
        messageList.add("Hello？")
        messageList.add("Are you here?")
        messageList.add("Hello")
        messageList.add("Hi, how's it going?")
        messageList.add("Good luck")
        notificationUtil.sendInboxNotification(pendingIntent, smallIcon, ticker, title, content,
                true, true, true, R.drawable.youcan, messageList)
    }

    private fun sendHeadsUp() {
        val notificationUtil = NotificationUtil(this, 4)
        var intent = Intent(this, TargetActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        var push = Intent()
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        push.setClass(this, NotificationActivity::class.java)

        var pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var pushPendingIntent = PendingIntent.getActivity(this, 0, push, PendingIntent.FLAG_CANCEL_CURRENT)
        var smallIcon = R.drawable.ic_neteasy
        var ticker = "You have a new notification"
        var title = "Hello World!!!"
        var content = "Congratulations! "
        notificationUtil.sendHeadsUpNotification(pendingIntent, pushPendingIntent, smallIcon, ticker, title, content, true, true, true)
    }
}