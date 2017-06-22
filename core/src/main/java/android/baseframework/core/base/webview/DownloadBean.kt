package android.baseframework.core.base.webview

import android.content.Context
import java.io.File
import java.io.Serializable

/**
 * Created by Neil Zheng on 2017/6/22.
 */

data class DownloadBean(val notificationId: Int, val url: String, val context: Context,
                        val file: File, val length: Long)