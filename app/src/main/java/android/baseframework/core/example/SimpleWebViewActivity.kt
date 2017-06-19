package android.baseframework.core.example

import android.baseframework.core.base.webview.BaseWebViewActivity
import android.baseframework.core.base.webview.IUrlListener
import android.baseframework.core.base.webview.SimpleUrlListener
import android.baseframework.core.utils.showToast
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.Toast

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class SimpleWebViewActivity: BaseWebViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUrl("https://www.baidu.com")
        addUrlListener(object : SimpleUrlListener() {
            override fun onPageFinished(view: WebView?, url: String?): Boolean {
                loadJs("document.querySelector('body').innerText = 'this text is modified by js'")
                loadJs("function f() {return 'this text is returned by js defined in Android'}")
                loadJs("f()", ValueCallback<String> { value ->
                    showToast(value)
                })
                return false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        })
    }


}
