package site.tsun.kotlinexamples.ui.appbarlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_app_bar.*
import site.tsun.kotlinexamples.R
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity




class AppBarLayoutActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_bar)
        fabShare.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.fabShare -> {
                    share()
                }
            }
        }
    }

    private fun share(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share")
        intent.putExtra(Intent.EXTRA_TEXT, this.getString(R.string.share_text))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(Intent.createChooser(intent, this.getString(R.string.share_text)))
    }
}