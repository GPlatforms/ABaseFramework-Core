package android.baseframework.core.base.webview

import android.baseframework.core.base.BaseCoreFragment
import android.baseframework.core.base.webview.handler.IChromeListener
import android.baseframework.core.base.webview.handler.IUrlListener
import android.baseframework.core.base.webview.widget.BaseWebView
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import com.github.ikidou.fragmentBackHandler.FragmentBackHandler

/**
 * Created by Neil Zheng on 2017/7/4.
 */

abstract class BaseWebViewFragment: BaseCoreFragment() {

    companion object {
        val EXTRA_URL = "EXTRA_URL"
        val EXTRA_TITLE = "EXTRA_TITLE"
        val EXTRA_RECEIVE_TITLE = "EXTRA_RECEIVE_TITLE"
        val REQUEST_UPLOAD_FILE = 3
    }

    private var url: String? = null
    private var title: String? = null
    private lateinit var webView: BaseWebView
    private var receiveTitleFlag = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = LinearLayoutCompat(context)
        root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

    override fun onBackPressed(): Boolean {
        return webView.handleBackAction()
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
        if (arguments.containsKey(EXTRA_URL)) {
            url = arguments.getString(EXTRA_URL)
        }
        if(arguments.containsKey(EXTRA_TITLE)) {
            title = arguments.getString(EXTRA_TITLE)
        }
        receiveTitleFlag = arguments.getBoolean(EXTRA_RECEIVE_TITLE, true)
    }

    private fun initWebView() {
        webView = WebFacade(this@BaseWebViewFragment)
                .showTitleBar(true)
                .showProgressBar(true)
                .receiveTitle(receiveTitleFlag)
                .url(url)
                .title(title)
                .build()
    }

}