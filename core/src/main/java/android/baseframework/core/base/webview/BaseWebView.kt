package android.baseframework.core.base.webview

import android.baseframework.core.BuildConfig
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import android.webkit.WebSettings
import android.os.Build
import android.webkit.CookieManager
import android.webkit.DownloadListener
import android.view.ViewGroup

/**
 * Created by Neil Zheng on 2017/6/15.
 */

open class BaseWebView : WebView {

    val webViewChromeClient: BaseWebChromeClient = BaseWebChromeClient(context)
    val webViewClient: BaseWebViewClient = BaseWebViewClient(context)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        }
        initSetting()
    }

    fun initSetting() {
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
        if (Build.VERSION.SDK_INT < 18) {
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        }
        if (Build.VERSION.SDK_INT < 19) {
            settings.setDatabasePath(context.filesDir.path);
        }
        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
        }
        setWebViewClient(webViewClient)
        setWebChromeClient(webViewChromeClient)
        setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            TODO("implement base download actions in BaseWebView")
        })
    }

    fun addUrlHandler(listener: IUrlListener) {
        webViewClient.addUrlListener(listener)
    }

    fun addChromeHandler(listener: IChromeListener) {
        webViewChromeClient.addChromeListener(listener)
    }

    override fun onResume() {
        super.onResume()
        resumeTimers()
    }

    override fun onPause() {
        super.onPause()
        pauseTimers()
    }

    fun onDestroy() {
        releaseCallback()
        try {
            (parent as ViewGroup).removeView(this@BaseWebView)
        } catch (ignored: Exception) {
        }
        try {
            removeAllViews()
        } catch (ignored: Exception) {
        }
        destroy()
    }

    fun releaseCallback() {
        if (android.os.Build.VERSION.SDK_INT < 16) {
            try {
                var field = WebView::class.java.getDeclaredField("mWebViewCore")
                field = field.type.getDeclaredField("mBrowserFrame")
                field = field.type.getDeclaredField("sConfigCallback")
                field.isAccessible = true
                field.set(null, null)
            } catch (e: NoSuchFieldException) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
            } catch (e: IllegalAccessException) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
            }
        } else {
            try {
                val sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback")
                if (sConfigCallback != null) {
                    sConfigCallback.isAccessible = true
                    sConfigCallback.set(null, null)
                }
            } catch (e: NoSuchFieldException) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
            } catch (e: ClassNotFoundException) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
            } catch (e: IllegalAccessException) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
            }

        }
    }

}
