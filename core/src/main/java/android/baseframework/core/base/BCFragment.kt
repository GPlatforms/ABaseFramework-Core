package android.baseframework.core.base

import android.baseframework.core.R
import android.view.View
import com.jayfeng.lesscode.core.ViewLess
import com.jayfeng.lesscode.headerbar.HeaderBar
import com.trello.rxlifecycle2.components.support.RxFragment


abstract class BCFragment : RxFragment() {

    var mHeaderBar: HeaderBar? = null

    /**
     * *****************************************************************
     * optional init base views: header bar, loading view
     * *****************************************************************
     */
    fun initHeaderBar(rootView: View, titleId: Int, showBack: Boolean) {
        initHeaderBar(rootView, getString(titleId), showBack)
    }

    fun initHeaderBar(rootView: View, title: String, showBack: Boolean) {
        mHeaderBar = ViewLess.`$`(rootView, R.id.header)
        mHeaderBar?.setTitle(title)
        if (showBack) {
            mHeaderBar?.showBack { activity.onBackPressed() }
        }
    }

    fun initHeaderBar(rootView: View, title: String) {
        initHeaderBar(rootView, title, false)
    }
}