package android.baseframework.core.example

import android.baseframework.core.base.webview.BaseWebViewActivity
import android.baseframework.core.base.webview.IUrlListener
import android.baseframework.core.base.webview.SimpleUrlListener
import android.baseframework.core.utils.showToast
import android.content.SharedPreferences
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.Toast

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class SimpleWebViewActivity : BaseWebViewActivity() {

    private var key = 0
    private val array = arrayOf("http://www.vip.com",
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
        key = getSharedPreferences("shared", 0).getInt("key", key)
        key %= array.size
        loadUrl(array[key])
        key++;
        getSharedPreferences("shared", 0).edit().putInt("key", key).apply()
    }
}
