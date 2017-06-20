package android.baseframework.core.base.webview

import android.baseframework.core.BuildConfig
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.os.Build
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class BaseWebView : WebView {

    private val webViewChromeClient: BaseWebChromeClient = BaseWebChromeClient(context)
    private val webViewClient: BaseWebViewClient = BaseWebViewClient(context)
    private val lifecycle: BaseWebViewLifecycle = BaseWebViewLifecycle(this@BaseWebView)
    private val jsCaller: JsCaller = JsCaller(this@BaseWebView)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        }
        initSetting()
    }

    private fun initSetting() {
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.domStorageEnabled = true
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.allowFileAccess = true
        settings.databaseEnabled = true
        settings.setGeolocationEnabled(true)
        settings.allowContentAccess = true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                || Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            removeJavascriptInterface("searchBoxJavaBridge_")
            removeJavascriptInterface("accessibility")
            removeJavascriptInterface("accessibilityTraversal")
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            settings.setDatabasePath(context.filesDir.path);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addJavascriptInterface(BaseJavaInterface(context), "Android")
        }
        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
        }
        setWebViewClient(webViewClient)
        setWebChromeClient(webViewChromeClient)
        setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            TODO("implement base download actions in BaseWebView")
        })
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        webViewChromeClient.onActivityResult(requestCode, resultCode, data)
    }

    fun callJs(js: String) {
        jsCaller.callJs(js)
    }

    fun callJs(js: String, callback: ValueCallback<String>) {
        jsCaller.callJs(js, callback)
    }

    fun addUrlHandler(listener: IUrlListener) {
        webViewClient.addUrlListener(listener)
    }

    fun addChromeHandler(listener: IChromeListener) {
        webViewChromeClient.addChromeListener(listener)
    }

    fun doPause() {
        lifecycle.onPause()
    }

    fun doResume() {
        lifecycle.onResume()
    }

    fun doDestroy() {
        lifecycle.onDestroy()
    }
}
