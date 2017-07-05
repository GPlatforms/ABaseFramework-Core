package android.baseframework.core.example.webview

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.base.webview.WebViewConfig
import android.baseframework.core.base.webview.widget.BaseWebView
import android.baseframework.core.example.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_webview_test_viewpager.*
import kotlinx.android.synthetic.main.activity_webview_test_viewpager.view.*

/**
 * Created by Neil Zheng on 2017/7/4.
 */

class SimpleWebViewViewPagerActivity : BaseCoreActivity() {

    private val list = arrayOf("http://www.vip.com",
            "https://h5.m.jd.com/active/download/download.html?channel=jd-msy1",
            "http://broken-links.com/tests/video/",
            "https://m.bilibili.com/video/av11484069.html",
            "http://www.taobao.com",
            "file:///android_asset/sms/sms.html",
            "")
    private val fragments = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test_viewpager)
        initView()
    }

    private fun initView() {
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment = getFragmentItem(position)
            override fun getCount(): Int = list.size
            override fun getPageTitle(position: Int): CharSequence = position.toString()
        }
        tabLayout.setViewPager(viewPager)
    }

    private fun getFragmentItem(position: Int): Fragment {
        if (fragments.isEmpty()) {
            initFragments()
        }
        return fragments[position]
    }

    private fun initFragments() {
        for ((index, string) in list.withIndex()) {
            val fragment = SimpleWebViewFragment()
            val arguments = Bundle()
            arguments.putString(WebViewConfig.EXTRA_URL, string)
            arguments.putString(WebViewConfig.EXTRA_TITLE, index.toString())
            arguments.putBoolean(WebViewConfig.EXTRA_RECEIVE_TITLE, index % 2 == 0)
            arguments.putBoolean(WebViewConfig.EXTRA_SHOW_TITLEBAR, true)
            arguments.putBoolean(WebViewConfig.EXTRA_SHOW_PROGRESSBAR, true)
            fragment.arguments = arguments
            fragments.add(fragment)
        }
    }
}
