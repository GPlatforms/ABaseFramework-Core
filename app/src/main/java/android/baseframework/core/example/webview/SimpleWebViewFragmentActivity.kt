package android.baseframework.core.example.webview

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.base.webview.WebViewConfig
import android.baseframework.core.example.R
import android.os.Bundle

/**
 * Created by Neil Zheng on 2017/7/4.
 */

class SimpleWebViewFragmentActivity: BaseCoreActivity() {

    private var url: String? = null
    private var title: String? = null
    private var receiveTitleFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test_webview_fragment)
        initIntentData()
        initData()
    }

    private fun initIntentData() {
        if (intent.hasExtra(WebViewConfig.EXTRA_URL)) {
            url = intent.getStringExtra(WebViewConfig.EXTRA_URL)
        }
        if(intent.hasExtra(WebViewConfig.EXTRA_TITLE)) {
            title = intent.getStringExtra(WebViewConfig.EXTRA_TITLE)
        }
        receiveTitleFlag = intent.getBooleanExtra(WebViewConfig.EXTRA_RECEIVE_TITLE, true)
    }

    private fun initData() {
        val fragment = SimpleWebViewFragment()
        val arguments = Bundle()
        arguments.putString(WebViewConfig.EXTRA_URL, url)
        arguments.putString(WebViewConfig.EXTRA_TITLE, title)
        arguments.putBoolean(WebViewConfig.EXTRA_RECEIVE_TITLE, receiveTitleFlag)
        fragment.arguments = arguments
        val transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.frame, fragment)
        transaction.commit()
    }

}