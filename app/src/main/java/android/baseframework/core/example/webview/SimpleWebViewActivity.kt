package android.baseframework.core.example.webview

import android.baseframework.core.base.webview.BaseWebViewActivity
import android.baseframework.core.base.webview.WebFacade
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.view.ViewGroup

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class SimpleWebViewActivity : BaseWebViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayoutCompat(this@SimpleWebViewActivity)
        root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(root)
        initWebView()
    }

    private fun initWebView() {
        webView = WebFacade(this@SimpleWebViewActivity)
                .showTitleBar(showTitleBar)
                .showProgressBar(showProgressBar)
                .receiveTitle(receiveTitleFlag)
                .url(url)
                .title(title)
                .build()
    }

}