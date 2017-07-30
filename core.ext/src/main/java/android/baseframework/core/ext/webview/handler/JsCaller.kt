package android.baseframework.core.ext.webview.handler

/**
 * Created by Neil Zheng on 2017/6/19.
 */

class JsCaller(val webView: android.webkit.WebView) {

    private val mHandler = android.os.Handler(android.os.Looper.getMainLooper())

    private fun callSafeCallJs(s: String, valueCallback: android.webkit.ValueCallback<String>?) {
        mHandler.post { callJs(s, valueCallback) }
    }

    fun callJs(js: String, callback: android.webkit.ValueCallback<String>?) {
        if (Thread.currentThread() !== android.os.Looper.getMainLooper().thread) {
            callSafeCallJs(js, callback)
            return
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            this.evaluateJs(js, callback)
        } else {
            this.loadJs(js)
        }
    }

    fun callJs(js: String) {
        this.callJs(js, null)
    }

    private fun loadJs(js: String) {
        webView.loadUrl(js)
    }

    @android.support.annotation.RequiresApi(android.os.Build.VERSION_CODES.KITKAT)
    private fun evaluateJs(js: String, callback: android.webkit.ValueCallback<String>?) {
        webView.evaluateJavascript(js, { value ->
            callback?.onReceiveValue(value)
        })
    }

    fun quickCallJs(method: String, callback: android.webkit.ValueCallback<String>?, vararg params: String) {
        val sb = StringBuilder()
        sb.append("javascript:" + method)
        if (params == null || params.size == 0) {
            sb.append("()")
        } else {
            sb.append("(").append(concat(*params)).append(")")
        }
        callJs(sb.toString(), callback)
    }

    private fun concat(vararg params: String): String {
        val mStringBuilder = StringBuilder()
        for (i in params.indices) {
            val param = params[i]
            if (!isJson(param)) {
                mStringBuilder.append("\"").append(param).append("\"")
            } else {
                mStringBuilder.append(param)
            }
            if (i != params.size - 1) {
                mStringBuilder.append(" , ")
            }
        }
        return mStringBuilder.toString()
    }

    fun quickCallJs(method: String, vararg params: String) {
        this.quickCallJs(method, null, *params)
    }

    fun quickCallJs(method: String) {
        this.quickCallJs(method, null)
    }

    fun isJson(param: String?): Boolean {
        if (android.text.TextUtils.isEmpty(param)) {
            return false
        }
        try {
            if (param!!.startsWith("[")) {
                org.json.JSONArray(param)
            } else {
                org.json.JSONObject(param)
            }
            return true
        } catch (ignored: org.json.JSONException) {
            return false
        }
    }

}