package site.tsun.kotlinexamples.fragment

import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_library.view.*
import kotlinx.android.synthetic.main.fragment_ui.view.*
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.adapter.FrameworkAdapter

class LibraryFragment : BaseFragment(), AdapterView.OnItemClickListener{


    private lateinit var data: Array<String>
    private var adapter: FrameworkAdapter? = null

    override fun initView(): View? {
        mView = View.inflate(mContext, R.layout.fragment_library, null)
        mView.lvFragment.onItemClickListener = this
        return mView
    }

    override fun initData() {
        data = arrayOf()
        adapter = FrameworkAdapter(mContext, data)
        mView.lvFragment.adapter = adapter
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val tmp = data[p2]
        when (tmp) {

        }
    }
}