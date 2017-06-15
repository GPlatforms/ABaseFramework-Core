package android.baseframework.core.example

import android.baseframework.core.base.webview.BaseWebViewActivity
import android.os.Bundle

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class SimpleWebViewActivity: BaseWebViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadUrl("http://www.baidu.com")
    }
}
