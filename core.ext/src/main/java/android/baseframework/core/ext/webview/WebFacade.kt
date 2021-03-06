package android.baseframework.core.ext.webview

import android.R
import android.app.Activity
import android.app.Fragment
import android.baseframework.core.ext.webview.handler.IChromeListener
import android.baseframework.core.ext.webview.handler.IUrlListener
import android.baseframework.core.ext.webview.handler.SimpleChromeListener
import android.baseframework.core.ext.webview.widget.BaseWebView
import android.baseframework.core.ext.webview.widget.DefaultProgressBar
import android.baseframework.core.ext.webview.widget.DefaultTitleBar
import android.content.Context
import android.os.Build
import android.support.v7.widget.LinearLayoutCompat
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.webkit.DownloadListener
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.support.v4.app.Fragment as SupportFragment

class WebFacade {

    private var context: Context
    private lateinit var webView: BaseWebView
    private lateinit var parent: ViewGroup
    private var layoutParams: ViewGroup.LayoutParams? = null
    private var chromeClients = arrayListOf<IChromeListener>()
    private var webClients = arrayListOf<IUrlListener>()
    private var title: String? = null
    private var url: String? = null
    private var showProgressBar = false
    private var showTitleBar = false
    private var receiveTitle = false
    private var videoProgress: View? = null
    private var titleBar: DefaultTitleBar? = null
    private var progressBar: DefaultProgressBar? = null
    private var downloadListener: DownloadListener? = null

    constructor(activity: Activity) {
        context = activity
        parent = activity.findViewById<ViewGroup>(android.R.id.content)
    }

    constructor(fragment: Fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context = fragment.context
        } else {
            context = fragment.activity
        }
        parent = fragment.view as ViewGroup
    }

    constructor(fragment: SupportFragment) {
        context = fragment.context!!
        parent = fragment.view as ViewGroup
    }

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

    fun setProgressBar(progressBar: DefaultProgressBar): WebFacade {
        this.progressBar = progressBar
        showProgressBar = true
        return this
    }

    fun showTitleBar(boolean: Boolean): WebFacade {
        showTitleBar = boolean
        return this
    }

    fun setTitleBar(titleBar: DefaultTitleBar): WebFacade {
        this.titleBar = titleBar
        showTitleBar = true
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

    fun url(url: String?): WebFacade {
        this.url = url
        return this
    }

    fun title(title: String?): WebFacade {
        this.title = title
        return this
    }

    fun build(): BaseWebView {
        webView = BaseWebView(context)
        for (item in chromeClients) {
            webView.addChromeHandler(item)
        }
        for (item in webClients) {
            webView.addUrlHandler(item)
        }
        if (null != downloadListener) {
            webView.setDownloadListener(downloadListener)
        }
        if (null == layoutParams) {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
        }
        val rootContainer = LinearLayoutCompat(context)
        rootContainer.orientation = LinearLayout.VERTICAL
        rootContainer.layoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        val webViewContainer = FrameLayout(context)
        webViewContainer.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT)
        rootContainer.addView(webViewContainer)
        webViewContainer.addView(webView)
        parent?.addView(rootContainer, layoutParams)
        if (showTitleBar) {
            if (null == titleBar) {
                inflateDefaultTitleBar()
            }
            rootContainer.addView(titleBar, 0)
        }
        if (showProgressBar) {
            if (null == progressBar) {
                inflateDefaultProgressBar()
            }
            webViewContainer.addView(progressBar)
        }
        if (showProgressBar || receiveTitle) {
            webView.addChromeHandler(object : SimpleChromeListener() {
                override fun onProgressChanged(view: WebView?, newProgress: Int): Boolean {
                    if (showProgressBar) {
                        when (newProgress) {
                            100 -> progressBar!!.visibility = View.GONE
                            else -> {
                                progressBar!!.progress = newProgress
                                progressBar!!.visibility = View.VISIBLE
                            }
                        }
                    }
                    return false
                }

                override fun onReceivedTitle(view: WebView?, title: String?): Boolean {
                    if (showTitleBar && receiveTitle && !TextUtils.isEmpty(title)) {
                        titleBar!!.title = title
                    }
                    return false
                }
            })
        }
        if (!TextUtils.isEmpty(title)) {
            titleBar!!.title = title
        }
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url)
        }
        return webView
    }

    private fun inflateDefaultTitleBar() {
        titleBar = DefaultTitleBar(context)
    }

    private fun inflateDefaultProgressBar() {
        progressBar = DefaultProgressBar(context, null, R.attr.progressBarStyleHorizontal,
                R.style.Widget_ProgressBar_Horizontal)
    }
}