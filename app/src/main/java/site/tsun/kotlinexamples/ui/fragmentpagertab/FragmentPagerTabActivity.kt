package site.tsun.kotlinexamples.ui.fragmentpagertab

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.fragment_pager_tab_main.*
import site.tsun.kotlinexamples.R
import site.tsun.kotlinexamples.fragment.BaseFragment
import site.tsun.kotlinexamples.ui.fragmentpagertab.fragment.ContactFragment
import site.tsun.kotlinexamples.ui.fragmentpagertab.fragment.FriendFragment
import site.tsun.kotlinexamples.ui.fragmentpagertab.fragment.SettingFragment
import site.tsun.kotlinexamples.ui.fragmentpagertab.fragment.WeixinFragment
import android.support.v4.app.FragmentPagerAdapter




class FragmentPagerTabActivity : FragmentActivity(), View.OnClickListener{

    private val list: ArrayList<BaseFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pager_tab_main)
        initData()
        initEvent()
    }

    private fun initData() {
        list.add(WeixinFragment())
        list.add(ContactFragment())
        list.add(FriendFragment())
        list.add(SettingFragment())
        var adapter: FragmentPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return list[position]
            }

            override fun getCount(): Int {
                return list.size
            }
        }
        vpFragmentPagerTabMain.adapter = adapter
    }

    private fun initEvent() {
        llBottom1.setOnClickListener(this)
        llBottom2.setOnClickListener(this)
        llBottom3.setOnClickListener(this)
        llBottom4.setOnClickListener(this)
        vpFragmentPagerTabMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                resetImage()
                when (position) {
                    0 -> {
                        ivWeixin.setImageResource(R.drawable.tab_weixin_pressed)
                        tvWeixin.setTextColor(Color.rgb(0, 255, 0))
                    }
                    1 -> {
                        ivContact.setImageResource(R.drawable.tab_contact_pressed)
                        tvContact.setTextColor(Color.rgb(0, 255, 0))
                    }
                    2 -> {
                        ivFriend.setImageResource(R.drawable.tab_friend_pressed)
                        tvFriend.setTextColor(Color.rgb(0, 255, 0))
                    }
                    3 -> {
                        ivSetting.setImageResource(R.drawable.tab_settings_pressed)
                        tvSetting.setTextColor(Color.rgb(0, 255, 0))
                    }
                }
            }

        })
    }

    private fun resetImage() {
        ivWeixin.setImageResource(R.drawable.tab_weixin_normal);
        ivContact.setImageResource(R.drawable.tab_contact_normal);
        ivFriend.setImageResource(R.drawable.tab_friend_normal);
        ivSetting.setImageResource(R.drawable.tab_settings_normal);

        tvWeixin.setTextColor(Color.rgb(204, 204, 204));
        tvContact.setTextColor(Color.rgb(204, 204, 204));
        tvFriend.setTextColor(Color.rgb(204, 204, 204));
        tvSetting.setTextColor(Color.rgb(204, 204, 204));
    }

    override fun onClick(v: View) {
        resetImage()
        when (v.getId()) {
            R.id.llBottom1 -> {
                ivWeixin.setImageResource(R.drawable.tab_weixin_pressed)
                tvWeixin.setTextColor(Color.rgb(0, 255, 0))
                vpFragmentPagerTabMain.setCurrentItem(0)
            }
            R.id.llBottom2 -> {
                ivContact.setImageResource(R.drawable.tab_contact_pressed)
                tvContact.setTextColor(Color.rgb(0, 255, 0))
                vpFragmentPagerTabMain.setCurrentItem(1)
            }
            R.id.llBottom3 -> {
                ivFriend.setImageResource(R.drawable.tab_friend_pressed)
                tvFriend.setTextColor(Color.rgb(0, 255, 0))
                vpFragmentPagerTabMain.setCurrentItem(2)
            }
            R.id.llBottom4 -> {
                ivSetting.setImageResource(R.drawable.tab_settings_pressed)
                tvSetting.setTextColor(Color.rgb(0, 255, 0))
                vpFragmentPagerTabMain.setCurrentItem(3)
            }
        }
    }
}