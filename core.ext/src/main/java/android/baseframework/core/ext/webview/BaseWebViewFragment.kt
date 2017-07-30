package android.baseframework.core.ext.webview

import android.baseframework.core.base.BaseCoreFragment
import android.baseframework.core.ext.webview.handler.IChromeListener
import android.baseframework.core.ext.webview.handler.IUrlListener
import android.baseframework.core.ext.webview.widget.BaseWebView
import android.content.Intent
import android.webkit.ValueCallback

/**
 * Created by Neil Zheng on 2017/7/4.
 */

abstract class BaseWebViewFragment: BaseCoreFragment() {

    protected var url: String? = null
    protected var title: String? = null
    protected var webView: BaseWebView? = null
    protected var receiveTitleFlag = true
    protected var showTitleBar = true
    protected var showProgressBar = true

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webView?.onActivityResult(requestCode, resultCode, data)
    }

    protected fun addUrlListener(listener: IUrlListener) {
        webView?.addUrlHandler(listener)
    }

    protected fun addChromeListener(listener: IChromeListener) {
        webView?.addChromeHandler(listener)
    }

    protected fun loadUrl(url: String) {
        webView?.loadUrl(url)
    }

    protected fun loadJs(js: String) {
        webView?.quickCallJs(js)
    }

    protected fun loadJs(js: String, callback: ValueCallback<String>?, vararg params: String) {
        webView?.quickCallJs(js, callback, *params)
    }

    protected fun reload() {
        webView?.reload()
    }

    override fun onBackPressed(): Boolean {
        return webView?.handleBackAction() ?: false
    }

    override fun onPause() {
        super.onPause()
        webView?.doPause()
    }

    override fun onResume() {
        super.onResume()
        webView?.doResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView?.doDestroy()
    }

    override fun initIntentData() {
        if (arguments.containsKey(WebViewConfig.EXTRA_URL)) {
            url = arguments.getString(WebViewConfig.EXTRA_URL)
        }
        if(arguments.containsKey(WebViewConfig.EXTRA_TITLE)) {
            title = arguments.getString(WebViewConfig.EXTRA_TITLE)
        }
        receiveTitleFlag = arguments.getBoolean(WebViewConfig.EXTRA_RECEIVE_TITLE, true)
        showTitleBar = arguments.getBoolean(WebViewConfig.EXTRA_SHOW_TITLEBAR, true)
        showProgressBar = arguments.getBoolean(WebViewConfig.EXTRA_SHOW_PROGRESSBAR, true)
    }

    override fun initData() {
        webView = WebFacade(this@BaseWebViewFragment)
                .showTitleBar(showTitleBar)
                .showProgressBar(showProgressBar)
                .receiveTitle(receiveTitleFlag)
                .url(url)
                .title(title)
                .build()
    }

}