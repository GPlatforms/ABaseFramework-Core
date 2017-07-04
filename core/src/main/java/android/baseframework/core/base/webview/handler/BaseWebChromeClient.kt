package android.baseframework.core.base.webview.handler

import android.net.Uri

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class BaseWebChromeClient(val webView: android.webkit.WebView, context: android.content.Context) : android.webkit.WebChromeClient() {

    private var chromeHandler: ChromeHandler = ChromeHandler(context)
    private var mJsCallJavas: MutableMap<String, JavaCaller> = java.util.HashMap()
    private var fileUploadHandler: UploadFileHandler = UploadFileHandler(context)
    private var videoHandler: WebVideoHandler = WebVideoHandler(webView, context)
    private lateinit var videoProgressView: android.view.View

    fun addChromeListener(listener: IChromeListener) {
        chromeHandler.addUrlListener(listener)
    }

    fun setListenerFinal(final: Boolean) {
        chromeHandler.setListenerFinal(final)
    }

    override fun onRequestFocus(view: android.webkit.WebView?) {
        if (!chromeHandler.onRequestFocus(view)) {
            super.onRequestFocus(view)
        }
    }

    override fun onJsAlert(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean {
        return chromeHandler.onJsAlert(view, url, message, result) || super.onJsAlert(view, url, message, result)
    }

    override fun onJsPrompt(view: android.webkit.WebView?, url: String?, message: String?, defaultValue: String?,
                            result: android.webkit.JsPromptResult?): Boolean {
        if (chromeHandler.onJsPrompt(view, url, message, defaultValue, result)) {
            return true
        } else if (JavaCaller.Companion.isSafeWebViewCallMsg(message)) {
            val jsonObject = JavaCaller.Companion.getMsgJSONObject(message)
            val interfacedName = JavaCaller.Companion.getInterfacedName(jsonObject)
            val jsCallJava = mJsCallJavas.get(interfacedName)
            if (jsCallJava != null && view != null) {
                result?.confirm(jsCallJava.call(view, jsonObject))
            }
            return true
        }
        return false
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
        fileUploadHandler.onActivityResult(requestCode, resultCode, data)
    }

    override fun onShowCustomView(view: android.view.View?, callback: android.webkit.WebChromeClient.CustomViewCallback?) {
        if (!chromeHandler.onShowCustomView(view, callback)) {
            videoHandler.onShowCustomView(view, callback)
        }
    }

    override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: android.webkit.GeolocationPermissions.Callback?) {
        if (!chromeHandler.onGeolocationPermissionsShowPrompt(origin, callback)) {
            super.onGeolocationPermissionsShowPrompt(origin, callback)
        }
    }

    override fun onPermissionRequest(request: android.webkit.PermissionRequest?) {
        if (!chromeHandler.onPermissionRequest(request)) {
            super.onPermissionRequest(request)
        }
    }

    override fun onConsoleMessage(consoleMessage: android.webkit.ConsoleMessage?): Boolean {
        return chromeHandler.onConsoleMessage(consoleMessage) || super.onConsoleMessage(consoleMessage)
    }

    override fun onPermissionRequestCanceled(request: android.webkit.PermissionRequest?) {
        if (!chromeHandler.onPermissionRequestCanceled(request)) {
            super.onPermissionRequestCanceled(request)
        }
    }

    override fun onShowFileChooser(webView: android.webkit.WebView?, filePathCallback: android.webkit.ValueCallback<Array<Uri>>?, fileChooserParams: android.webkit.WebChromeClient.FileChooserParams?): Boolean {
        if (chromeHandler.onShowFileChooser(webView, filePathCallback, fileChooserParams)) {
            return true
        } else {
            return fileUploadHandler.chooseFileWithArrayCallback(filePathCallback, fileChooserParams)
        }
    }

    override fun onReceivedTouchIconUrl(view: android.webkit.WebView?, url: String?, precomposed: Boolean) {
        if (!chromeHandler.onReceivedTouchIconUrl(view, url, precomposed)) {
            super.onReceivedTouchIconUrl(view, url, precomposed)
        }
    }

    override fun onReceivedIcon(view: android.webkit.WebView?, icon: android.graphics.Bitmap?) {
        if (!chromeHandler.onReceivedIcon(view, icon)) {
            super.onReceivedIcon(view, icon)
        }
    }

    override fun onReceivedTitle(view: android.webkit.WebView?, title: String?) {
        if (!chromeHandler.onReceivedTitle(view, title)) {
            super.onReceivedTitle(view, title)
        }
    }

    override fun onProgressChanged(view: android.webkit.WebView?, newProgress: Int) {
        if (!chromeHandler.onProgressChanged(view, newProgress)) {
            super.onProgressChanged(view, newProgress)
        }
    }

    override fun onJsConfirm(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean {
        return chromeHandler.onJsConfirm(view, url, message, result) || super.onJsConfirm(view, url, message, result)
    }

    override fun getVisitedHistory(callback: android.webkit.ValueCallback<Array<String>>?) {
        if (!chromeHandler.getVisitedHistory(callback)) {
            super.getVisitedHistory(callback)
        }
    }

    override fun onGeolocationPermissionsHidePrompt() {
        if (!chromeHandler.onGeolocationPermissionsHidePrompt()) {
            super.onGeolocationPermissionsHidePrompt()
        }
    }

    override fun onJsBeforeUnload(view: android.webkit.WebView?, url: String?, message: String?, result: android.webkit.JsResult?): Boolean {
        return chromeHandler.onJsBeforeUnload(view, url, message, result)
                || super.onJsBeforeUnload(view, url, message, result)
    }


    override fun onHideCustomView() {
        if (!chromeHandler.onHideCustomView()) {
            videoHandler.onHideCustomView()
            super.onHideCustomView()
        }
    }

    override fun onCreateWindow(view: android.webkit.WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: android.os.Message?): Boolean {
        return chromeHandler.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
                || super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

    override fun onCloseWindow(window: android.webkit.WebView?) {
        if (!chromeHandler.onCloseWindow(window)) {
            super.onCloseWindow(window)
        }
    }

    override fun getVideoLoadingProgressView(): android.view.View {
        var view = chromeHandler.getVideoLoadingProgressView()
        if (null != view) {
            return view
        } else {
            view = getDefaultVideoLoadingProgressView()
            if (null != view) {
                return view
            }
        }
        return super.getVideoLoadingProgressView()
    }

    private fun getDefaultVideoLoadingProgressView(): android.view.View? {
        //TODO("style the VideoProgress layout")
        return null
    }
}
