package android.baseframework.core.base.webview

import android.app.Activity
import android.app.Fragment
import android.baseframework.core.R
import android.baseframework.core.base.webview.handler.IChromeListener
import android.baseframework.core.base.webview.handler.IUrlListener
import android.baseframework.core.base.webview.widget.BaseWebView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.webkit.DownloadListener

/**
 * Created by Neil Zheng on 2017/7/3.
 */

class WebFacade(var activity: Activity) {

    constructor(fragment: Fragment) : this(fragment.activity)

    private var webView: BaseWebView? = null
    private var parent: ViewGroup? = null
    private var layoutParams: ViewGroup.LayoutParams? = null
    private var chromeClients = arrayListOf<IChromeListener>()
    private var webClients = arrayListOf<IUrlListener>()
    private var url: String? = null
    private var showProgressBar = false
    private var showTitleBar = false
    private var receiveTitle = false
    private var videoProgress: View? = null
    private var downloadListener: DownloadListener? = null

    fun parent(parent: ViewGroup,
               layoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                       ViewGroup.LayoutParams.MATCH_PARENT)): WebFacade {
        this.parent = parent
        this.layoutParams = layoutParams
        return this
    }

    fun chromeClients(vararg chromeClient: IChromeListener): WebFacade {
        chromeClients.addAll(chromeClient)
        return this
    }

    fun webClients(vararg webClient: IUrlListener): WebFacade {
        webClients.addAll(webClient)
        return this
    }

    fun showProgressBar(boolean: Boolean): WebFacade {
        showProgressBar = boolean
        return this
    }

    fun showTitleBar(boolean: Boolean): WebFacade {
        showTitleBar = boolean
        return this
    }

    fun receiveTitle(boolean: Boolean): WebFacade {
        receiveTitle = boolean
        return this
    }

    fun videoProgress(videoProgress: View): WebFacade {
        this.videoProgress = videoProgress
        return this
    }

    fun downloadListener(downloadListener: DownloadListener): WebFacade {
        this.downloadListener = downloadListener
        return this
    }

    fun url(url: String): WebFacade {
        this.url = url
        return this
    }

    fun build() {
        val view = View.inflate(activity, R.layout.layout_webview_facade, null)
        webView = view.findViewById(R.id.webView) as BaseWebView
        for (item in chromeClients) {
            webView!!.addChromeHandler(item)
        }
        for (item in webClients) {
            webView!!.addUrlHandler(item)
        }
        if (null != downloadListener) {
            webView!!.setDownloadListener(downloadListener)
        }
        parent?.addView(view, layoutParams)
        if (!TextUtils.isEmpty(url)) {
            webView!!.loadUrl(url)
        }
    }
}