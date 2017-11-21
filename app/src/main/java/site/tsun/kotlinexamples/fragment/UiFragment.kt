package site.tsun.kotlinexamples.fragment

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_ui.view.*
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.adapter.FrameworkAdapter
import site.tsun.kotlinexamples.common.webview.WebViewActivity
import site.tsun.kotlinexamples.ui.appbarlayout.AppBarLayoutActivity
import site.tsun.kotlinexamples.ui.fragmentpagertab.FragmentPagerTabActivity

class UiFragment : BaseFragment(), AdapterView.OnItemClickListener {

    private lateinit var data: Array<String>
    private var adapter: FrameworkAdapter? = null

    override fun initView(): View? {
        mView = View.inflate(mContext, R.layout.fragment_ui, null)
        mView.lvUi.onItemClickListener = this
        return mView
    }

    override fun initData() {
        data = arrayOf( "FragmentPagerTab", "AppBarLayout")
        adapter = FrameworkAdapter(mContext, data)
        mView.lvUi.adapter = adapter
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val tmp = data[p2]
        when (tmp) {
            "FragmentPagerTab" -> {
                val intent = Intent(mContext, FragmentPagerTabActivity::class.java)
                startActivity(intent)
            }
            "AppBarLayout" -> {
                val intent = Intent(mContext, AppBarLayoutActivity::class.java)
                startActivity(intent)
            }
        }
    }
}