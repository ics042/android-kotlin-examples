package site.tsun.kotlinexamples.fragment

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_other.view.*
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.adapter.FrameworkAdapter
import site.tsun.kotlinexamples.common.webview.WebViewActivity
import site.tsun.kotlinexamples.other.architecture.lifecycles.ChronoActivity
import site.tsun.kotlinexamples.other.architecture.rxjava.ui.UserActivity
import site.tsun.kotlinexamples.ui.appbarlayout.AppBarLayoutActivity
import site.tsun.kotlinexamples.ui.fragmentpagertab.FragmentPagerTabActivity

class OtherFragment : BaseFragment(), AdapterView.OnItemClickListener {

    private lateinit var data: Array<String>
    private var adapter: FrameworkAdapter? = null

    override fun initView(): View? {
        mView = View.inflate(mContext, R.layout.fragment_other, null)
        mView.lvOther.onItemClickListener = this
        return mView
    }

    override fun initData() {
        data = arrayOf( "Arch Components RxJava", "Lifecycle")
        adapter = FrameworkAdapter(mContext, data)
        mView.lvOther.adapter = adapter
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val tmp = data[p2]
        when (tmp) {
            "Arch Components RxJava" -> {
                val intent = Intent(mContext, UserActivity::class.java)
                startActivity(intent)
            }
            "Lifecycle" -> {
                val intent = Intent(mContext, ChronoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}