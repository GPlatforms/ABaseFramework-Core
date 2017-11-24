package android.baseframework.core.widget

import android.baseframework.core.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout

import com.jayfeng.lesscode.core.ViewLess

class BCStateView : RelativeLayout {

    lateinit var mLoadingContainer: View
    lateinit var mEmptyContainer: View
    lateinit var mErrorContainer: View
    lateinit var mRetryView: Button

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.bc_view_state, this, true)
        mLoadingContainer = ViewLess.`$`(this, R.id.bc_state_loading_container)
        mEmptyContainer = ViewLess.`$`(this, R.id.bc_state_empty_container)
        mErrorContainer = ViewLess.`$`(this, R.id.bc_state_error_container)
        mRetryView = ViewLess.`$`(this, R.id.bc_state_error_retry)
    }

    fun showLoading() {
        mLoadingContainer.visibility = View.VISIBLE
        mEmptyContainer.visibility = View.GONE
        mErrorContainer.visibility = View.GONE
    }

    fun showEmpty() {
        mLoadingContainer.visibility = View.GONE
        mEmptyContainer.visibility = View.VISIBLE
        mErrorContainer.visibility = View.GONE
    }

    fun showError() {
        mLoadingContainer.visibility = View.GONE
        mEmptyContainer.visibility = View.GONE
        mErrorContainer.visibility = View.VISIBLE
    }

    fun setRetryClickListener(listener: OnClickListener) {
        mRetryView.setOnClickListener(listener)
    }

}
