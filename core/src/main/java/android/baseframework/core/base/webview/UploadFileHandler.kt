package android.baseframework.core.base.webview

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 * Created by Neil Zheng on 2017/6/19.
 */

class UploadFileHandler(val activity: Activity) {

    var arrayCallback: ValueCallback<Array<Uri>>? = null
    var singleCallback: ValueCallback<Uri>? = null

    fun chooseFile(filePathCallback: ValueCallback<Array<Uri>>?,
                   fileChooserParams: WebChromeClient.FileChooserParams?): Boolean {
        arrayCallback = filePathCallback
        if (fileChooserParams != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivityForResult(fileChooserParams.createIntent(), BaseWebViewActivity.REQUEST_UPLOAD_FILE)
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                    BaseWebViewActivity.REQUEST_UPLOAD_FILE)
        }
        return false
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (BaseWebViewActivity.REQUEST_UPLOAD_FILE != requestCode) {
            return
        }
        if (resultCode == Activity.RESULT_CANCELED) {
//            if (jsChannel) {
//                mJsChannelCallback.call(null)
//                return
//            }
            if (arrayCallback != null) {
                arrayCallback!!.onReceiveValue(null)
            }
            if (singleCallback != null) {
                singleCallback!!.onReceiveValue(null)
            }
            return
        }
        if (resultCode == Activity.RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (arrayCallback == null) {
                    return
                }
                val array: Array<Uri>? = handleData(data)
                arrayCallback!!.onReceiveValue(if (array == null) arrayOf<Uri>() else array)
//            } else if (jsChannel) {
//                convertFileAndCallBack(handleData(data))
            } else {
                val mUri = data?.data
                if (singleCallback != null) {
                    singleCallback!!.onReceiveValue(mUri)
                }
            }
        }
    }

    private fun handleData(data: Intent?): Array<Uri>? {
        if (data == null) {
            return null
        }
        val target = data.dataString
        if (!TextUtils.isEmpty(target)) {
            return arrayOf(Uri.parse(target))
        }
        return null
    }

}