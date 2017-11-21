package site.tsun.kotlinexamples.common.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_webview.*
import site.tsun.kotlinexamples.R
import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        wvMain.loadUrl("http://www.baidu.com")
        wvMain.setWebChromeClient(object : WebChromeClient() {
            override fun onReceivedIcon(view: WebView, icon: Bitmap) {
                super.onReceivedIcon(view, icon)
            }
        })
        wvMain.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, url)
            }
        })
    }
}