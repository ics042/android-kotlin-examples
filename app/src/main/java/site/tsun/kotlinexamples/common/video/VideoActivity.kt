package site.tsun.kotlinexamples.common.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_video.*
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.adapter.FrameworkAdapter


class VideoActivity : AppCompatActivity(), AdapterView.OnItemClickListener {


    private lateinit var data: Array<String>
    private var adapter: FrameworkAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        data = arrayOf("1.mp4", "2.mp4", "3.mp4", "1.rmvb", "2.rmvb", "3.rmvb")
        adapter = FrameworkAdapter(this, data)
        lvVideo.adapter = adapter
        lvVideo.setOnItemClickListener(this)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val tmp = data[p2]
        val newVideoPath = "http://192.168.178.22/video/"+tmp
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(newVideoPath), "video/*")
        startActivity(intent)
    }
}