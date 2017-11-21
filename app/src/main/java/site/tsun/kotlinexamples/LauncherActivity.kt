package site.tsun.kotlinexamples

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.content.Intent




class LauncherActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        Handler().postDelayed(Runnable { startMainActivity() }, 2000)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}