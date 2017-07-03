package android.baseframework.core.base.webview.handler

import android.content.Context
import java.io.File
import java.io.Serializable

/**
 * Created by Neil Zheng on 2017/6/22.
 */

data class DownloadBean(val notificationId: Int, val url: String, val context: android.content.Context,
                        val file: java.io.File, val length: Long)