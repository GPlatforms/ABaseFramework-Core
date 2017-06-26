package android.baseframework.core.base.webview

import android.app.PendingIntent
import android.baseframework.core.R
import android.baseframework.core.utils.closeSafely
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.orhanobut.logger.Logger
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Neil Zheng on 2017/6/22.
 */

class DownloadTask(val bean: DownloadBean) : AsyncTask<Unit, Int, Int>() {

    private var loaded: Long = 0L
    private val totals: Long = -1L
    private var tmp: Long = 0L
    private var begin: Long = 0L
    private var used: Long = 1L
    private val mTimeLast: Long = 0L
    private val mSpeed: Long = 0L
    private val TIME_OUT = 30000000
    private var time: Long = 0L
    private lateinit var downloadNotification: DownloadNotification
    private val ERROR_LOAD = -5

    override fun doInBackground(vararg params: Unit?): Int {
        var result = ERROR_LOAD
        try {
            begin = System.currentTimeMillis()
            if (!checkDownLoaderCondition())
                return ERROR_LOAD
            result = doDownLoad()
        } catch (e: Exception) {
            e.printStackTrace()
            return ERROR_LOAD
        }
        return result
    }

    override fun onProgressUpdate(vararg values: Int?) {
        var progress : Int = 0
        if(!values.isEmpty()) {
            progress = values[0]!!
        } else {
            val current = System.currentTimeMillis()
            used = current - begin
            val c = System.currentTimeMillis()
            if (c - time > 800) {
                time = c
            }
            progress = ((tmp + loaded) / totals.toFloat() * 100).toInt()
        }
        downloadNotification.setProgress(100, progress, false)
    }

    override fun onPostExecute(result: Int?) {
        if (result == ERROR_LOAD) {
            Toast.makeText(bean.context, "下载出错了", Toast.LENGTH_SHORT).show()
            downloadNotification.cancel(bean.notificationId)
            return
        }
        downloadNotification.cancel(bean.notificationId)
        val intent = WebViewUtils.getIntentCompat(bean.context, bean.file)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val rightPendIntent = PendingIntent.getActivity(bean.context,
                0x110, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        downloadNotification.setProgressFinish("点击打开", rightPendIntent)
    }

    override fun onCancelled() {
        super.onCancelled()
    }

    override fun onCancelled(result: Int?) {
        super.onCancelled(result)
    }

    override fun onPreExecute() {
        super.onPreExecute()
        buildNotify(Intent(), "正在下载中")
    }

    private fun buildNotify(intent: Intent, progressHint: String) {
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val rightPendIntent = PendingIntent.getActivity(bean.context, 0x33, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val ticker = "您有一条新通知"
        downloadNotification = DownloadNotification(bean.notificationId, bean.context)
        downloadNotification.notify_progress(rightPendIntent, android.R.mipmap.sym_def_app_icon, ticker,
                "文件下载", progressHint, false, false, false)
        downloadNotification.sent()
    }

    @Throws(IOException::class)
    private fun doDownLoad(): Int {
        val mHttpURLConnection = URL(bean.url).openConnection() as HttpURLConnection
        mHttpURLConnection.setRequestProperty("Accept", "application/*")
        mHttpURLConnection.connectTimeout = 5000
        if (bean.file.length() > 0) {
            tmp = bean.file.length()
            mHttpURLConnection.addRequestProperty("Range", "bytes=$tmp-")
        }
        mHttpURLConnection.connect()
        if (mHttpURLConnection.responseCode != 200 && mHttpURLConnection.responseCode != 206) {
            return ERROR_LOAD
        }
        try {
            return doDownLoad(mHttpURLConnection.inputStream, object : RandomAccessFile(bean.file, "rw") {
                @Throws(IOException::class)
                override fun write(buffer: ByteArray, offset: Int, count: Int) {
                    super.write(buffer, offset, count)
                    loaded += count.toLong()
                    publishProgress((loaded / bean.length.toFloat() * 100).toInt())
                }
            })
        } finally {
            mHttpURLConnection.disconnect()
        }
    }

    private fun checkDownLoaderCondition(): Boolean {
        if (!checkNetwork())
            return false
        if (bean.length - bean.file.length() > WebViewUtils.getAvailableStorage()) {
            return false
        }
        return true
    }

    private fun doDownLoad(input: InputStream, output: RandomAccessFile): Int {
        val buffer = ByteArray(102400)
        val bis = BufferedInputStream(input, 102400)
        try {
            output.seek(output.length())
            var bytes = 0
            var previousBlockTime: Long = -1L
            while (!isCancelled) {
                val n = bis.read(buffer, 0, 102400)
                if (n == -1) {
                    break
                }
                output.write(buffer, 0, n)
                bytes += n
                if (!checkNetwork()) {
                    return ERROR_LOAD
                }
                if (mSpeed != 0L) {
                    previousBlockTime = -1L
                } else if (previousBlockTime == -1L) {
                    previousBlockTime = System.currentTimeMillis()
                } else if (System.currentTimeMillis() - previousBlockTime > TIME_OUT) {
                    return ERROR_LOAD
                }
            }
            return bytes
        } finally {
            output.closeSafely()
            bis.closeSafely()
            input.closeSafely()
        }
    }

    private fun checkNetwork(): Boolean {
        return WebViewUtils.checkNetwork(bean.context)
    }
}
