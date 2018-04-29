package site.tsun.kotlinexamples.fragment

import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_common.*
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.adapter.FrameworkAdapter
import android.content.Intent
import kotlinx.android.synthetic.main.fragment_common.view.*
import site.tsun.kotlinexamples.common.fcm.FcmDemoActivity
import site.tsun.kotlinexamples.common.notification.NotificationActivity
import site.tsun.kotlinexamples.common.pdf.PdfActivity
import site.tsun.kotlinexamples.common.video.VideoActivity
import site.tsun.kotlinexamples.common.webview.WebViewActivity

class CommonFragment : BaseFragment(), AdapterView.OnItemClickListener {

    private lateinit var data: Array<String>
    private var adapter: FrameworkAdapter? = null

    override fun initView(): View? {
        mView = View.inflate(mContext, R.layout.fragment_common, null)
        mView.lvCommon.setOnItemClickListener(this)
        return mView
    }

    override fun initData() {
        data = arrayOf("WebView", "Notification", "FCM", "Video", "PDF")
        adapter = FrameworkAdapter(mContext, data)
        mView.lvCommon.adapter = adapter
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val tmp = data[p2]
        when (tmp) {
            "WebView" -> {
                val intent = Intent(mContext, WebViewActivity::class.java)
                startActivity(intent)
            }
            "Notification" -> {
                val intent = Intent(mContext, NotificationActivity::class.java)
                startActivity(intent)
            }
            "FCM" -> {
                val intent = Intent(mContext, FcmDemoActivity::class.java)
                startActivity(intent)
            }
            "Video" -> {
                val intent = Intent(mContext, VideoActivity::class.java)
                startActivity(intent)
            }
            "PDF" -> {
                val intent = Intent(mContext, PdfActivity::class.java)
                startActivity(intent)
            }
        }
    }
}