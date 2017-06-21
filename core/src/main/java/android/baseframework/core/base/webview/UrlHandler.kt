package android.baseframework.core.base.webview

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.KeyEvent
import android.webkit.*
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

/**
 * Created by Neil Zheng on 2017/6/15.
 */


class UrlHandler(val context: Context) {

    private var mListeners: ArrayList<IUrlListener> = ArrayList()
    private var mListenerFinal = false
    private var handler = Handler(context.mainLooper)

    /**
     * add a new IUrlListener at the last of the {@link #mListeners}
     * @param listener the new listener
     */
    fun addUrlListener(listener: IUrlListener) {
        if(!mListenerFinal) mListeners.add(0, listener)
    }

    /**
     * the default value of {@link #mListenerFinal} is FALSE. You CANNOT reverse the value of it since you set it to
     * TRUE. After been set to TRUE, new {@link IUrlListener} will never be saved to the list anymore.
     * @param final the value to set
     */
    fun setListenerFinal(final: Boolean) {
        if(!mListenerFinal && final) {
            mListenerFinal = final
            mListeners.trimToSize()
        }
    }

    fun onPageFinished(view: WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPageFinished(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.shouldOverrideKeyEvent(view, event)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.shouldOverrideUrlLoading(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.doUpdateVisitedHistory(view, url, isReload)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedError(view, request, error)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedLoginRequest(view: WebView?, realm: String?, account: String?, args: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedLoginRequest(view, realm, account, args)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?,
                            errorResponse: WebResourceResponse?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedHttpError(view, request, errorResponse)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPageStarted(view, url, favicon)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onScaleChanged(view, oldScale, newScale)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onPageCommitVisible(view: WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPageCommitVisible(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onUnhandledKeyEvent(view, event)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedClientCertRequest(view, request)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?,
                                  realm: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedHttpAuthRequest(view, handler, host, realm)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedSslError(view, handler, error)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onFormResubmission(view, dontResend, resend)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onLoadResource(view: WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onLoadResource(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun runOnUiThread(task: Runnable) {
        if (Thread.currentThread() !== Looper.getMainLooper().thread) {
            handler.post(task)
        } else {
            task.run()
        }
    }

}