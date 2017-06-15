package android.baseframework.core.base.webview

import android.baseframework.core.R
import android.baseframework.core.base.BaseCoreActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
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

    private fun initIntentData() {
        if (intent.hasExtra(EXTRA_URL)) {
            url = intent.getStringExtra(EXTRA_URL)
        }
        newWindowFlag = intent.getBooleanExtra(EXTRA_NEW_WINDOW, false)
        receiveTitleFlag = intent.getBooleanExtra(EXTRA_RECEIVE_TITLE, true)
    }

    fun initWebView() {
        addUrlListener(object : SimpleUrlListener() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
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

    fun addUrlListener(listener: IUrlListener) {
        webView.addUrlHandler(listener)
    }

    fun addChromeListener(listener: IChromeListener) {
        var view = findViewById(R.id.webView)
        webView.addChromeHandler(listener)
    }

    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    fun reload() {
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
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.onDestroy()
    }
}