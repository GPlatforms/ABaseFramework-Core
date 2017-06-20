package android.baseframework.core.base.webview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import android.webkit.*
import java.util.HashMap

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class BaseWebChromeClient(context: Context) : WebChromeClient() {

    private var chromeHandler: ChromeHandler = ChromeHandler(context)
    private var mJsCallJavas: MutableMap<String, JavaCaller> = HashMap()
    private var fileUploadHandler: UploadFileHandler = UploadFileHandler(context as Activity)

    fun addChromeListener(listener: IChromeListener) {
        chromeHandler.addUrlListener(listener)
    }

    fun setListenerFinal(final: Boolean) {
        chromeHandler.setListenerFinal(final)
    }

    override fun onRequestFocus(view: WebView?) {
        if (!chromeHandler.onRequestFocus(view)) {
            super.onRequestFocus(view)
        }
    }

    override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
        return chromeHandler.onJsAlert(view, url, message, result) || super.onJsAlert(view, url, message, result)
    }

    override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?,
                            result: JsPromptResult?): Boolean {
        if (chromeHandler.onJsPrompt(view, url, message, defaultValue, result)) {
            return true
        } else if (JavaCaller.isSafeWebViewCallMsg(message)) {
            val jsonObject = JavaCaller.getMsgJSONObject(message)
            val interfacedName = JavaCaller.getInterfacedName(jsonObject)
            val jsCallJava = mJsCallJavas.get(interfacedName)
            if (jsCallJava != null && view != null) {
                result?.confirm(jsCallJava.call(view, jsonObject))
            }
            return true
        }
        return false
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        fileUploadHandler.onActivityResult(requestCode, resultCode, data)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        if (!chromeHandler.onShowCustomView(view, callback)) {
            super.onShowCustomView(view, callback)
        }
    }

    override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?) {
        if (!chromeHandler.onGeolocationPermissionsShowPrompt(origin, callback)) {
            super.onGeolocationPermissionsShowPrompt(origin, callback)
        }
    }

    override fun onPermissionRequest(request: PermissionRequest?) {
        if (!chromeHandler.onPermissionRequest(request)) {
            super.onPermissionRequest(request)
        }
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        return chromeHandler.onConsoleMessage(consoleMessage) || super.onConsoleMessage(consoleMessage)
    }

    override fun onPermissionRequestCanceled(request: PermissionRequest?) {
        if (!chromeHandler.onPermissionRequestCanceled(request)) {
            super.onPermissionRequestCanceled(request)
        }
    }

    override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?): Boolean {
        if (chromeHandler.onShowFileChooser(webView, filePathCallback, fileChooserParams)) {
            return true
        } else {
            return fileUploadHandler.chooseFile(filePathCallback, fileChooserParams)
        }
    }

    override fun onReceivedTouchIconUrl(view: WebView?, url: String?, precomposed: Boolean) {
        if (!chromeHandler.onReceivedTouchIconUrl(view, url, precomposed)) {
            super.onReceivedTouchIconUrl(view, url, precomposed)
        }
    }

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        if (!chromeHandler.onReceivedIcon(view, icon)) {
            super.onReceivedIcon(view, icon)
        }
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        if (!chromeHandler.onReceivedTitle(view, title)) {
            super.onReceivedTitle(view, title)
        }
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        if (!chromeHandler.onProgressChanged(view, newProgress)) {
            super.onProgressChanged(view, newProgress)
        }
    }

    override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
        return chromeHandler.onJsConfirm(view, url, message, result) || super.onJsConfirm(view, url, message, result)
    }

    override fun getVisitedHistory(callback: ValueCallback<Array<String>>?) {
        if (!chromeHandler.getVisitedHistory(callback)) {
            super.getVisitedHistory(callback)
        }
    }

    override fun onGeolocationPermissionsHidePrompt() {
        if (!chromeHandler.onGeolocationPermissionsHidePrompt()) {
            super.onGeolocationPermissionsHidePrompt()
        }
    }

    override fun onJsBeforeUnload(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
        return chromeHandler.onJsBeforeUnload(view, url, message, result)
                || super.onJsBeforeUnload(view, url, message, result)
    }

    override fun onHideCustomView() {
        if (!chromeHandler.onHideCustomView()) {
            super.onHideCustomView()
        }
    }

    override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
        return chromeHandler.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
                || super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

    override fun onCloseWindow(window: WebView?) {
        if (!chromeHandler.onCloseWindow(window)) {
            super.onCloseWindow(window)
        }
    }
}