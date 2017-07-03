package android.baseframework.core.example.webview

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.base.webview.WebFacade
import android.baseframework.core.example.R
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_webview_test2_webview.*

/**
 * Created by Neil Zheng on 2017/6/26.
 */

class Test2WebViewActivity : BaseCoreActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test2_webview)
        WebFacade.with(this).parent(layout_root).url("http://www.taobao.com").build()
    }
}