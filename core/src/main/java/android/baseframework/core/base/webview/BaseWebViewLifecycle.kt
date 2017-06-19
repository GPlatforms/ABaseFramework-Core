package android.baseframework.core.base.webview

import android.os.Build
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

/**
 * Created by Neil Zheng on 2017/6/19.
 */

class BaseWebViewLifecycle(val webView: WebView) : ILifecycle {

    override fun onResume() {
        webView.onResume()
        webView.resumeTimers()
    }

    override fun onPause() {
        webView.pauseTimers()
        webView.onPause()
    }

    override fun onDestroy() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return
        }
        webView.loadUrl("about:blank")
        webView.stopLoading()
        if (webView.handler != null) {
            webView.handler.removeCallbacksAndMessages(null)
        }
        webView.removeAllViews()
        if (webView.parent is ViewGroup) {
            val vg = webView.parent as ViewGroup
            vg.removeView(webView)
            vg.removeAllViewsInLayout()
        }
        webView.setWebChromeClient(null)
        webView.setWebViewClient(null)
        webView.tag = null
        webView.visibility = View.GONE
        webView.clearHistory()
        webView.removeAllViewsInLayout()
        releaseConfigCallback()
        webView.destroy()
    }

    /**
     * this function is called to avoid MemoryLeak happening
     */
    private fun releaseConfigCallback() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) { // JELLY_BEAN
            try {
                var field = WebView::class.java.getDeclaredField("mWebViewCore")
                field = field.type.getDeclaredField("mBrowserFrame")
                field = field.type.getDeclaredField("sConfigCallback")
                field.isAccessible = true
                field.set(null, null)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) { // KITKAT
            try {
                val sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback")
                if (sConfigCallback != null) {
                    sConfigCallback.isAccessible = true
                    sConfigCallback.set(null, null)
                }
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
    }
}
