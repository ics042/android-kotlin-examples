package site.tsun.kotlinexamples.ui.fragmentpagertab.fragment

import android.view.View
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.fragment.BaseFragment

class SettingFragment : BaseFragment(){

    override fun initView(): View? {
        mView = View.inflate(mContext, R.layout.fragment_tab_setting, null)

        return mView
    }

    override fun initData() {

    }
}