package site.tsun.kotlinexamples.common.notification

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import site.tsun.kotlinexamples.R
import android.provider.Settings.ACTION_SETTINGS
import android.content.Intent
import android.os.Build
import android.provider.Settings


class TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_target)
        goToSettings()

    }

    private fun goToSettings() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
//            // 进入设置系统应用权限界面
//            val intent = Intent(Settings.ACTION_SETTINGS)
//            startActivity(intent)
//            return
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
//            // 进入设置系统应用权限界面
//            val intent = Intent(Settings.ACTION_SETTINGS)
//            startActivity(intent)
//            return
//        }
        val intent = Intent()
        intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"

        //for Android 5-7
        intent.putExtra("app_package", packageName)
        intent.putExtra("app_uid", applicationInfo.uid)

        // for Android O
        intent.putExtra("android.provider.extra.APP_PACKAGE", packageName)

        startActivity(intent)
    }
}