package android.baseframework.core.example.webview

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.base.webview.WebViewConfig
import android.baseframework.core.example.R
import android.baseframework.core.utils.startActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_webview_test_webview.*

/**
 * Created by Neil Zheng on 2017/6/26.
 */

class TestWebViewActivity : BaseCoreActivity() {

    private val list = arrayOf("http://www.vip.com",
            "https://h5.m.jd.com/active/download/download.html?channel=jd-msy1",
            "file:///android_asset/upload_file/uploadfile.html",
            "file:///android_asset/upload_file/jsuploadfile.html",
            "file:///android_asset/js_interaction/hello.html",
            "http://broken-links.com/tests/video/",
            "https://m.bilibili.com/video/av11484069.html",
            "http://www.taobao.com",
            "http://www.wandoujia.com/apps",
            "file:///android_asset/sms/sms.html",
            "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test_webview)
        initView()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this@TestWebViewActivity)
        val adapter = MySimpleRecyclerAdapter(list)
        recyclerView.adapter = adapter
        adapter.setOnClickListener(object : BaseViewHolder.onItemClickListener {
            override fun onItemClick(position: Int) {
                doItemClick(list[position])
            }
        })
        radio_7.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                startActivity(SimpleWebViewViewPagerActivity::class.java)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(radio_7.isChecked) {
            radio_7.isChecked = false
            radio_1.isChecked = true
        }
    }

    private fun doItemClick(url: String) {
        val intent: Intent = when (radioGroup.checkedRadioButtonId) {
            R.id.radio_1 -> {
                Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
            }
            R.id.radio_2 -> {
                intent = Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
                intent.putExtra(WebViewConfig.EXTRA_SHOW_TITLEBAR, false)
                intent
            }
            R.id.radio_3 -> {
                intent = Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
                intent.putExtra(WebViewConfig.EXTRA_SHOW_PROGRESSBAR, false)
                intent
            }
            R.id.radio_4 -> {
                intent = Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
                intent.putExtra(WebViewConfig.EXTRA_RECEIVE_TITLE, false)
                intent.putExtra(WebViewConfig.EXTRA_TITLE, "我十动然拒了所有网页标题")
                intent
            }
            R.id.radio_5 -> {
                Intent(this@TestWebViewActivity, SizedWebViewActivity::class.java)
            }
            R.id.radio_6 -> {
                Intent(this@TestWebViewActivity, SimpleWebViewFragmentActivity::class.java)
            }
            else -> Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
        }
        intent.putExtra(WebViewConfig.EXTRA_URL, url)
        startActivity(intent)
    }
}