package android.baseframework.core.example.webview

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.example.R
import android.baseframework.core.utils.startActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
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
        recyclerView.adapter = MySimpleRecyclerAdapter(list)
    }
}