package android.baseframework.core.ext.webview.handler

/**
 * Created by Neil Zheng on 2017/6/22.
 */

data class DownloadBean(val notificationId: Int, val url: String, val context: android.content.Context,
                        val file: java.io.File, val length: Long)