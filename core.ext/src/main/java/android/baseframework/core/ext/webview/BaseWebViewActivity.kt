package android.baseframework.core.ext.webview

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.ext.webview.handler.IChromeListener
import android.baseframework.core.ext.webview.handler.IUrlListener
import android.baseframework.core.ext.webview.widget.BaseWebView
import android.content.Intent
import android.os.Bundle
import android.webkit.ValueCallback


abstract class BaseWebViewActivity : BaseCoreActivity() {

    companion object {
        val REQUEST_UPLOAD_FILE = 3
    }

    protected var url: String? = null
    protected var title: String? = null
    protected lateinit var webView: BaseWebView
    protected var receiveTitleFlag = true
    protected var showTitleBar = true
    protected var showProgressBar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntentData()
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

    protected open fun initIntentData() {
        if (intent.hasExtra(WebViewConfig.EXTRA_URL)) {
            url = intent.getStringExtra(WebViewConfig.EXTRA_URL)
        }
        if(intent.hasExtra(WebViewConfig.EXTRA_TITLE)) {
            title = intent.getStringExtra(WebViewConfig.EXTRA_TITLE)
        }
        receiveTitleFlag = intent.getBooleanExtra(WebViewConfig.EXTRA_RECEIVE_TITLE, true)
        showTitleBar = intent.getBooleanExtra(WebViewConfig.EXTRA_SHOW_TITLEBAR, true)
        showProgressBar = intent.getBooleanExtra(WebViewConfig.EXTRA_SHOW_PROGRESSBAR, true)
    }
}