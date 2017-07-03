package android.baseframework.core.base.webview.handler

import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.view.View
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

interface IUrlListener {

    fun onPageFinished(view: android.webkit.WebView?, url: String?): Boolean

    fun shouldOverrideKeyEvent(view: android.webkit.WebView?, event: android.view.KeyEvent?): Boolean

    fun doUpdateVisitedHistory(view: android.webkit.WebView?, url: String?, isReload: Boolean): Boolean

    fun onReceivedError(view: android.webkit.WebView?, request: android.webkit.WebResourceRequest?, error: android.webkit.WebResourceError?): Boolean

    fun onReceivedLoginRequest(view: android.webkit.WebView?, realm: String?, account: String?, args: String?): Boolean

    fun onReceivedHttpError(view: android.webkit.WebView?, request: android.webkit.WebResourceRequest?, errorResponse: android.webkit.WebResourceResponse?): Boolean

    fun onPageStarted(view: android.webkit.WebView?, url: String?, favicon: android.graphics.Bitmap?): Boolean

    fun onScaleChanged(view: android.webkit.WebView?, oldScale: Float, newScale: Float): Boolean

    fun shouldOverrideUrlLoading(view: android.webkit.WebView?, url: String?): Boolean

    fun onPageCommitVisible(view: android.webkit.WebView?, url: String?): Boolean

    fun onUnhandledKeyEvent(view: android.webkit.WebView?, event: android.view.KeyEvent?): Boolean

    fun onReceivedClientCertRequest(view: android.webkit.WebView?, request: android.webkit.ClientCertRequest?): Boolean

    fun onReceivedHttpAuthRequest(view: android.webkit.WebView?, handler: android.webkit.HttpAuthHandler?, host: String?, realm: String?): Boolean

    fun onReceivedSslError(view: android.webkit.WebView?, handler: android.webkit.SslErrorHandler?, error: android.net.http.SslError?): Boolean

    fun onFormResubmission(view: android.webkit.WebView?, dontResend: android.os.Message?, resend: android.os.Message?): Boolean

    fun onLoadResource(view: android.webkit.WebView?, url: String?): Boolean

}
