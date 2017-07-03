package android.baseframework.core.base.webview.handler

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface


/**
 * Created by Neil Zheng on 2017/6/20.
 */

class BaseJavaInterface(val context: android.content.Context) {

    @android.webkit.JavascriptInterface
    fun dial(phone: String) {
        val intent = android.content.Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + phone))
        context.startActivity(intent)
    }



}
