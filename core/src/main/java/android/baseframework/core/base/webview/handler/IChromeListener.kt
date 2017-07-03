package android.baseframework.core.base.webview.handler

import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

interface IChromeListener {

    fun onRequestFocus(view: android.webkit.WebView?): Boolean

    fun onJsAlert(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean

    fun onJsPrompt(view: android.webkit.WebView?, url: String?, message: String?, defaultValue: String?,
                   result: android.webkit.JsPromptResult?): Boolean

    fun onShowCustomView(view: android.view.View?, callback: android.webkit.WebChromeClient.CustomViewCallback?): Boolean

    fun onGeolocationPermissionsShowPrompt(origin: String?, callback: android.webkit.GeolocationPermissions.Callback?): Boolean

    fun onPermissionRequest(request: android.webkit.PermissionRequest?): Boolean

    fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage?): Boolean

    fun onPermissionRequestCanceled(request: android.webkit.PermissionRequest?): Boolean

    fun onShowFileChooser(webView: android.webkit.WebView?, filePathCallback: android.webkit.ValueCallback<Array<Uri>>?,
                          fileChooserParams: android.webkit.WebChromeClient.FileChooserParams?): Boolean

    fun onReceivedTouchIconUrl(view: android.webkit.WebView?, url: String?, precomposed: Boolean): Boolean

    fun onReceivedIcon(view: android.webkit.WebView?, icon: android.graphics.Bitmap?): Boolean

    fun onReceivedTitle(view: android.webkit.WebView?, title: String?): Boolean

    fun onProgressChanged(view: android.webkit.WebView?, newProgress: Int): Boolean

    fun onJsConfirm(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean

    fun getVisitedHistory(callback: android.webkit.ValueCallback<Array<String>>?): Boolean

    fun onGeolocationPermissionsHidePrompt(): Boolean

    fun onJsBeforeUnload(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean

    fun onHideCustomView(): Boolean

    fun onCreateWindow(view: android.webkit.WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: android.os.Message?): Boolean

    fun onCloseWindow(window: android.webkit.WebView?): Boolean

    fun getVideoLoadingProgressView(): android.view.View?

}
