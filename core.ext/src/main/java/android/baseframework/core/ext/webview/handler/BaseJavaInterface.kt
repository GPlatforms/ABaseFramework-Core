package android.baseframework.core.ext.webview.handler

import android.net.Uri


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
