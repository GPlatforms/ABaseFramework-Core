package android.baseframework.core.base.webview

import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

open class BaseWebViewClient(context: Context) : WebViewClient() {

    private var urlHandler: UrlHandler = UrlHandler(context)

    fun addUrlListener(listener: IUrlListener) {
        urlHandler.addUrlListener(listener)
    }

    fun setListenerFinal(final: Boolean) {
        urlHandler.setListenerFinal(final)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        if(!urlHandler.onPageFinished(view, url)) {
            super.onPageFinished(view, url)
        }
    }

    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return urlHandler.shouldOverrideKeyEvent(view, event) || super.shouldOverrideKeyEvent(view, event)
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        if(!urlHandler.doUpdateVisitedHistory(view, url, isReload)) {
            super.doUpdateVisitedHistory(view, url, isReload)
        }
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        if(!urlHandler.onReceivedError(view, request, error)) {
            super.onReceivedError(view, request, error)
        }
    }

    override fun onReceivedLoginRequest(view: WebView?, realm: String?, account: String?, args: String?) {
        if(!urlHandler.onReceivedLoginRequest(view, realm, account, args)) {
            super.onReceivedLoginRequest(view, realm, account, args)
        }
    }

    override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
        if(!urlHandler.onReceivedHttpError(view, request, errorResponse)) {
            super.onReceivedHttpError(view, request, errorResponse)
        }
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        if(!urlHandler.onPageStarted(view, url, favicon)) {
            super.onPageStarted(view, url, favicon)
        }
    }

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
        if(!urlHandler.onScaleChanged(view, oldScale, newScale)) {
            super.onScaleChanged(view, oldScale, newScale)
        }
    }

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        if(!urlHandler.onPageCommitVisible(view, url)) {
            super.onPageCommitVisible(view, url)
        }
    }

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        if(!urlHandler.onUnhandledKeyEvent(view, event)) {
            super.onUnhandledKeyEvent(view, event)
        }
    }

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?) {
        if(!urlHandler.onReceivedClientCertRequest(view, request)) {
            super.onReceivedClientCertRequest(view, request)
        }
    }

    override fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?, realm: String?) {
        if(!urlHandler.onReceivedHttpAuthRequest(view, handler, host, realm)) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm)
        }
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        if(!urlHandler.onReceivedSslError(view, handler, error)) {
            super.onReceivedSslError(view, handler, error)
        }
    }

    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        if(!urlHandler.onFormResubmission(view, dontResend, resend)) {
            super.onFormResubmission(view, dontResend, resend)
        }
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        if(!urlHandler.onLoadResource(view, url)) {
            super.onLoadResource(view, url)
        }
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return urlHandler.shouldOverrideUrlLoading(view, request) || super.shouldOverrideUrlLoading(view, request)
    }
}
