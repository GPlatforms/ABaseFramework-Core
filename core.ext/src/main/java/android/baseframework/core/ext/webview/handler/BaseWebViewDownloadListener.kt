package android.baseframework.core.ext.webview.handler

import android.baseframework.core.ext.webview.WebViewConfig
import android.baseframework.core.ext.webview.WebViewUtils
import java.io.File


class BaseWebViewDownloadListener(val context: android.content.Context): android.webkit.DownloadListener {

    var downloadId = 0

    override fun onDownloadStart(url: String, userAgent: String,
                                 contentDisposition: String, mimeType: String, contentLength: Long) {
        val mFile = getFile(contentDisposition, url)
        if (mFile != null && mFile.exists() && mFile.length() >= contentLength) {
            val mIntent = WebViewUtils.Companion.getIntentCompat(context, mFile)
            context.startActivity(mIntent)
            return
        }
        if (mFile != null) {
            DownloadTask(DownloadBean(downloadId++, url, context, mFile, contentLength)).execute()
        }
    }

    private fun getFile(contentDisposition: String, url: String): java.io.File? {
        try {
            var filename = ""
            if (!android.text.TextUtils.isEmpty(contentDisposition) && contentDisposition.contains("filename") && !contentDisposition.endsWith("filename")) {
                val position = contentDisposition.indexOf("filename")
                filename = contentDisposition.substring(position + 1)
            }
            if (android.text.TextUtils.isEmpty(filename) && !android.text.TextUtils.isEmpty(url) && !url.endsWith("/")) {
                val p = url.lastIndexOf("/")
                if (p != -1)
                    filename = url.substring(p + 1)
                if (filename.contains("?")) {
                    val index = filename.indexOf("?")
                    filename = filename.substring(0, index)
                }
            }
            if (android.text.TextUtils.isEmpty(filename)) {
                filename = System.currentTimeMillis().toString() + ""
            }
            val mFile = java.io.File(android.os.Environment.getExternalStorageDirectory().absolutePath + File.separator
                    + WebViewConfig.DOWNLOAD_PATH, filename)
            if (!mFile.exists())
                mFile.createNewFile()
            return mFile
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}
