package android.baseframework.core.base.webview.handler

/**
 * Created by Neil Zheng on 2017/6/15.
 */

abstract class SimpleUrlListener : IUrlListener {

    override fun shouldOverrideKeyEvent(view: android.webkit.WebView?, event: android.view.KeyEvent?): Boolean = false

    override fun doUpdateVisitedHistory(view: android.webkit.WebView?, url: String?, isReload: Boolean): Boolean = false

    override fun onReceivedError(view: android.webkit.WebView?, request: android.webkit.WebResourceRequest?,
                                 error: android.webkit.WebResourceError?): Boolean = false

    override fun onReceivedLoginRequest(view: android.webkit.WebView?, realm: String?, account: String?,
                                        args: String?): Boolean = false

    override fun onReceivedHttpError(view: android.webkit.WebView?, request: android.webkit.WebResourceRequest?,
                                     errorResponse: android.webkit.WebResourceResponse?): Boolean = false

    override fun onPageStarted(view: android.webkit.WebView?, url: String?, favicon: android.graphics.Bitmap?): Boolean = false

    override fun onScaleChanged(view: android.webkit.WebView?, oldScale: Float, newScale: Float): Boolean = false

    override fun onPageCommitVisible(view: android.webkit.WebView?, url: String?): Boolean = false

    override fun onUnhandledKeyEvent(view: android.webkit.WebView?, event: android.view.KeyEvent?): Boolean = false

    override fun onReceivedClientCertRequest(view: android.webkit.WebView?, request: android.webkit.ClientCertRequest?): Boolean = false

    override fun onReceivedHttpAuthRequest(view: android.webkit.WebView?, handler: android.webkit.HttpAuthHandler?, host: String?,
                                           realm: String?): Boolean = false

    override fun onReceivedSslError(view: android.webkit.WebView?, handler: android.webkit.SslErrorHandler?, error: android.net.http.SslError?): Boolean = false

    override fun onFormResubmission(view: android.webkit.WebView?, dontResend: android.os.Message?, resend: android.os.Message?): Boolean = false

    override fun onLoadResource(view: android.webkit.WebView?, url: String?): Boolean = false

}
