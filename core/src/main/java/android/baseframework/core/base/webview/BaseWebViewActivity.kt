package android.baseframework.core.base.webview

import android.baseframework.core.R
import android.baseframework.core.base.BaseCoreActivity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebResourceRequest
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_base_webview.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

abstract class BaseWebViewActivity : BaseCoreActivity() {

    companion object {
        val EXTRA_URL = "EXTRA_URL"
        val EXTRA_NEW_WINDOW = "EXTRA_NEW_WINDOW"
        val EXTRA_RECEIVE_TITLE = "EXTRA_RECEIVE_TITLE"
        val REQUEST_UPLOAD_FILE = 3
    }

    var url: String? = null
    var newWindowFlag = false
    var receiveTitleFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_webview)
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
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
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
        if (intent.hasExtra(EXTRA_URL)) {
            url = intent.getStringExtra(EXTRA_URL)
        }
        newWindowFlag = intent.getBooleanExtra(EXTRA_NEW_WINDOW, false)
        receiveTitleFlag = intent.getBooleanExtra(EXTRA_RECEIVE_TITLE, true)
    }

    private fun initWebView() {
        addUrlListener(object : SimpleUrlListener() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?): Boolean {
                return false
            }
        })
        addChromeListener(object : SimpleChromeListener() {
            override fun onProgressChanged(view: WebView?, newProgress: Int): Boolean {
                when (newProgress) {
                    100 -> progressBar.visibility = View.GONE
                    else -> {
                        progressBar.progress = newProgress
                        progressBar.visibility = View.VISIBLE
                    }
                }
                return false
            }

            override fun onReceivedTitle(view: WebView?, title: String?): Boolean {
                if (receiveTitleFlag && !TextUtils.isEmpty(title)) {
                    supportActionBar?.title = title
                }
                return false
            }
        })
    }
}