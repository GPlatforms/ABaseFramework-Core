package android.baseframework.core.ext.webview.handler

import android.net.Uri

/**
 * Created by Neil Zheng on 2017/6/15.
 */

abstract class SimpleChromeListener : IChromeListener {

    override fun onRequestFocus(view: android.webkit.WebView?): Boolean = false

    override fun onJsAlert(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean = false

    override fun onJsPrompt(view: android.webkit.WebView?, url: String?, message: String?, defaultValue: String?, result:
    android.webkit.JsPromptResult?): Boolean = false

    override fun onShowCustomView(view: android.view.View?, callback: android.webkit.WebChromeClient.CustomViewCallback?): Boolean = false

    override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: android.webkit.GeolocationPermissions.Callback?):
            Boolean = false

    override fun onPermissionRequest(request: android.webkit.PermissionRequest?): Boolean = false

    override fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage?): Boolean = false

    override fun onPermissionRequestCanceled(request: android.webkit.PermissionRequest?): Boolean = false

    override fun onShowFileChooser(webView: android.webkit.WebView?, filePathCallback: android.webkit.ValueCallback<Array<Uri>>?,
                                   fileChooserParams: android.webkit.WebChromeClient.FileChooserParams?): Boolean = false

    override fun onReceivedTouchIconUrl(view: android.webkit.WebView?, url: String?, precomposed: Boolean): Boolean = false

    override fun onReceivedIcon(view: android.webkit.WebView?, icon: android.graphics.Bitmap?): Boolean = false

    override fun onReceivedTitle(view: android.webkit.WebView?, title: String?): Boolean = false

    override fun onProgressChanged(view: android.webkit.WebView?, newProgress: Int): Boolean = false

    override fun onJsConfirm(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean = false

    override fun getVisitedHistory(callback: android.webkit.ValueCallback<Array<String>>?): Boolean = false

    override fun onGeolocationPermissionsHidePrompt(): Boolean = false

    override fun onJsBeforeUnload(view: android.webkit.WebView?, url: String?, message: String?,
                                  result: android.webkit.JsResult?): Boolean = false

    override fun onHideCustomView(): Boolean = false

    override fun onCreateWindow(view: android.webkit.WebView?, isDialog: Boolean, isUserGesture: Boolean,
                                resultMsg: android.os.Message?): Boolean = false

    override fun onCloseWindow(window: android.webkit.WebView?): Boolean = false

    override fun getVideoLoadingProgressView(): android.view.View? = null
}
