package android.baseframework.core.base.webview

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.base.webview.handler.IChromeListener
import android.baseframework.core.base.webview.handler.IUrlListener
import android.baseframework.core.base.webview.widget.BaseWebView
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.view.ViewGroup
import android.webkit.ValueCallback

/**
 * Created by Neil Zheng on 2017/6/15.
 */

abstract class BaseWebViewActivity : BaseCoreActivity() {

    companion object {
        val REQUEST_UPLOAD_FILE = 3
    }

    private var url: String? = null
    private var title: String? = null
    private lateinit var webView: BaseWebView
    private var receiveTitleFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayoutCompat(this@BaseWebViewActivity)
        root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(root)
        initIntentData()
        initWebView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webView.onActivityResult(requestCode, resultCode, data)
    }

    protected fun addUrlListener(listener: IUrlListener) {
        webView.addUrlHandler(listener)
    }

    protected fun addChromeListener(listener: IChromeListener) {
        webView.addChromeHandler(listener)
    }

    protected fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    protected fun loadJs(js: String) {
        webView.quickCallJs(js)
    }

    protected fun loadJs(js: String, callback: ValueCallback<String>?, vararg params: String) {
        webView.quickCallJs(js, callback, *params)
    }

    protected fun reload() {
        webView.reload()
    }

    override fun onBackPressed() {
        if (!webView.handleBackAction()) {
            super.onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()
        webView.doPause()
    }

    override fun onResume() {
        super.onResume()
        webView.doResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.doDestroy()
    }

    private fun initIntentData() {
        if (intent.hasExtra(WebViewConfig.EXTRA_URL)) {
            url = intent.getStringExtra(WebViewConfig.EXTRA_URL)
        }
        if(intent.hasExtra(WebViewConfig.EXTRA_TITLE)) {
            title = intent.getStringExtra(WebViewConfig.EXTRA_TITLE)
        }
        receiveTitleFlag = intent.getBooleanExtra(WebViewConfig.EXTRA_RECEIVE_TITLE, true)
    }

    private fun initWebView() {
        webView = WebFacade(this@BaseWebViewActivity)
                .showTitleBar(true)
                .showProgressBar(true)
                .receiveTitle(receiveTitleFlag)
                .url(url)
                .title(title)
                .build()
    }
}