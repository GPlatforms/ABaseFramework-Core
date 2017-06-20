package android.baseframework.core.base.webview

import android.util.Log
import android.webkit.WebView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.ref.WeakReference

/**
 * Created by Neil Zheng on 2017/6/19.
 */

class JsCallback(view: WebView, injectedName: String?, index: Int) {

    private val CALLBACK_JS_FORMAT = "javascript:%s.callback(%d, %d %s);"
    private var mIndex: Int
    private var mCouldGoOn: Boolean = false
    private var mWebViewRef: WeakReference<WebView>
    private var mIsPermanent: Int = 0
    private var mInjectedName: String?

    init {
        mCouldGoOn = true
        mWebViewRef = WeakReference(view)
        mInjectedName = injectedName
        mIndex = index
    }

    /**
     * 向网页执行js回调；
     * @param args
     * *
     * @throws JsCallbackException
     */
    @Throws(JsCallbackException::class)
    fun apply(vararg args: Any) {
        if (mWebViewRef.get() == null) {
            throw JsCallbackException("the WebView related to the JsCallback has been recycled")
        }
        if (!mCouldGoOn) {
            throw JsCallbackException("the JsCallback isn't permanent,cannot be called more than once")
        }
        val sb = StringBuilder()
        for (arg in args) {
            sb.append(",")
            val isStrArg = arg is String
            // 有的接口将Json对象转换成了String返回，这里不能加双引号，否则网页会认为是String而不是JavaScript对象；
            val isObjArg = isJavaScriptObject(arg)
            if (isStrArg && !isObjArg) {
                sb.append("\"")
            }
            sb.append(arg.toString())
            if (isStrArg && !isObjArg) {
                sb.append("\"")
            }
        }
        val execJs = String.format(CALLBACK_JS_FORMAT, mInjectedName, mIndex, mIsPermanent, sb.toString())
        mWebViewRef.get()!!.loadUrl(execJs)
        mCouldGoOn = mIsPermanent > 0
    }

    /**
     * 是否是JSON(JavaScript Object Notation)对象；
     * @param obj
     * *
     * @return
     */
    private fun isJavaScriptObject(obj: Any): Boolean {
        if (obj is JSONObject || obj is JSONArray) {
            return true
        } else {
            val json = obj.toString()
            try {
                JSONObject(json)
            } catch (e: JSONException) {
                try {
                    JSONArray(json)
                } catch (e1: JSONException) {
                    return false
                }

            }

            return true
        }
    }

    /**
     * 一般传入到Java方法的js function是一次性使用的，即在Java层jsCallback.apply(...)之后不能再发起回调了；
     * 如果需要传入的function能够在当前页面生命周期内多次使用，请在第一次apply前setPermanent(true)；
     * @param value
     */
    fun setPermanent(value: Boolean) {
        mIsPermanent = if (value) 1 else 0
    }

    class JsCallbackException(msg: String) : Exception(msg)
}