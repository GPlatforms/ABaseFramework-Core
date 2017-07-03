package android.baseframework.core.base.webview.handler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import com.orhanobut.logger.Logger

/**
 * Created by Neil Zheng on 2017/6/19.
 */

class UploadFileHandler(val context: android.content.Context) {

    var activity: android.app.Activity? = null
    var callback: android.webkit.ValueCallback<Array<Uri>>? = null

    init {
        if(context is android.app.Activity) {
            activity = context
        }
    }

    fun chooseFileWithArrayCallback(filePathCallback: android.webkit.ValueCallback<Array<Uri>>?,
                                    fileChooserParams: android.webkit.WebChromeClient.FileChooserParams?): Boolean {
        callback = filePathCallback
        chooseFile(fileChooserParams)
        return true
    }

    private fun chooseFile(fileChooserParams: android.webkit.WebChromeClient.FileChooserParams?) {
        if (fileChooserParams != null && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity?.startActivityForResult(fileChooserParams.createIntent(), android.baseframework.core.base.webview.BaseWebViewActivity.Companion.REQUEST_UPLOAD_FILE)
        } else {
            val intent = android.content.Intent(android.content.Intent.ACTION_GET_CONTENT)
            intent.addCategory(android.content.Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            activity?.startActivityForResult(android.content.Intent.createChooser(intent, "File Chooser"),
                    android.baseframework.core.base.webview.BaseWebViewActivity.Companion.REQUEST_UPLOAD_FILE)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
        if (android.baseframework.core.base.webview.BaseWebViewActivity.Companion.REQUEST_UPLOAD_FILE != requestCode) {
            return
        }
        if (resultCode == android.app.Activity.RESULT_CANCELED) {
            if (callback != null) {
                callback!!.onReceiveValue(null)
            }
        }
        if (resultCode == android.app.Activity.RESULT_OK) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && callback != null) {
                com.orhanobut.logger.Logger.e("3")
                val array: Array<android.net.Uri>? = handleData(data)
                callback!!.onReceiveValue(array)
            }
        }
    }

    private fun handleData(data: android.content.Intent?): Array<android.net.Uri>? {
        if (data == null) {
            return null
        }
        val target = data.dataString
        if (!android.text.TextUtils.isEmpty(target)) {
            return arrayOf(android.net.Uri.parse(target))
        }
        return null
    }

}