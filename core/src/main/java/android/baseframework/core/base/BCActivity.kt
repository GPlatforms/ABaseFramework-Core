package android.baseframework.core.base

import android.baseframework.core.R
import android.os.Bundle
import com.jayfeng.lesscode.core.ViewLess
import com.jayfeng.lesscode.headerbar.HeaderBar
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BCActivity : RxAppCompatActivity() {

    var mHeaderBar: HeaderBar? = null

    /**
     * *****************************************************************
     * optional init base views: header bar, loading view
     * *****************************************************************
     */
    fun initHeaderBar(titleId: Int, showBack: Boolean) {
        initHeaderBar(getString(titleId), showBack)
    }

    fun initHeaderBar(title: String, showBack: Boolean) {
        mHeaderBar = ViewLess.`$`(this, R.id.header)
        mHeaderBar?.setTitle(title)
        if (showBack) {
            mHeaderBar?.showBack { onBackPressed() }
        }
    }

    fun initHeaderBar(title: String) {
        mHeaderBar = ViewLess.`$`(this, R.id.header)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}