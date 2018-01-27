package site.tsun.kotlinexamples

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*
import site.tsun.kotlinexamples.fragment.*
import java.util.ArrayList
import site.tsun.kotlinexamples.fragment.BaseFragment




class MainActivity : FragmentActivity() {

    private lateinit var mBaseFragment: ArrayList<BaseFragment>
    private var pos: Int = 0
    private var lastFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        setListener()
        rgMain.check(R.id.rbCommon)
    }

    private fun initFragment() {
        mBaseFragment = ArrayList()
        mBaseFragment.add(CommonFragment())
        mBaseFragment.add(LibraryFragment())
        mBaseFragment.add(UiFragment())
        mBaseFragment.add(OtherFragment())
    }

    private fun setListener() {
        rgMain.setOnCheckedChangeListener(MyOnCheckedChangeListener())
    }

    inner class MyOnCheckedChangeListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
            when (p1) {
                R.id.rbCommon -> {
                    pos = 0;
                }
                R.id.rbFramework -> {
                    pos = 1;
                }
                R.id.rbUi -> {
                    pos = 2;
                }
                R.id.rbOther -> {
                    pos = 3;
                }
            }
            var to: BaseFragment = getFragment()
            switchFragment(lastFragment, to)
        }

    }

    private fun switchFragment(from: Fragment?, to: BaseFragment) {
        if (from != to && to != null) {
            // Get FragmentManager
            val fm: FragmentManager = getSupportFragmentManager()
            // Start transaction
            val transaction: FragmentTransaction = fm.beginTransaction()
            lastFragment = to
            if (!to.isAdded()) {
                if (from != null) {
                    transaction.hide(from);
                }
                transaction.add(R.id.fl_content, to).commit();
            } else {
                if (from != null) {
                    transaction.hide(from);
                }
                transaction.show(to).commit();
            }
        }
    }

    private fun getFragment(): BaseFragment {
        return mBaseFragment[pos]
    }
}

