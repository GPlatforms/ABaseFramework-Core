package android.baseframework.core.widget

import android.baseframework.core.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout

class BCStateView : RelativeLayout {

    val mCustomContainer by lazy { findViewById<FrameLayout>(R.id.bc_state_custom_container) }
    val mLoadingContainer by lazy { findViewById<View>(R.id.bc_state_loading_container) }
    val mEmptyContainer by lazy { findViewById<View>(R.id.bc_state_empty_container) }
    val mErrorContainer by lazy { findViewById<View>(R.id.bc_state_error_container) }
    val mRetryView by lazy { findViewById<Button>(R.id.bc_state_error_retry) }

    constructor(context: Context?) : super(context) {
        init(null, 0)
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context?, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.bc_view_state, this, true)
    }

    fun setCustomView(customView: View) {
        mCustomContainer.removeAllViews()
        mCustomContainer.addView(customView)
    }

    fun showCustomView() {
        mCustomContainer.visibility = View.VISIBLE
        mLoadingContainer.visibility = View.GONE
        mEmptyContainer.visibility = View.GONE
        mErrorContainer.visibility = View.GONE
    }

    fun showLoading() {
        mCustomContainer.visibility = View.GONE
        mLoadingContainer.visibility = View.VISIBLE
        mEmptyContainer.visibility = View.GONE
        mErrorContainer.visibility = View.GONE
    }

    fun showEmpty() {
        mCustomContainer.visibility = View.GONE
        mLoadingContainer.visibility = View.GONE
        mEmptyContainer.visibility = View.VISIBLE
        mErrorContainer.visibility = View.GONE
    }

    fun showError() {
        mCustomContainer.visibility = View.GONE
        mLoadingContainer.visibility = View.GONE
        mEmptyContainer.visibility = View.GONE
        mErrorContainer.visibility = View.VISIBLE
    }

    fun setRetryClickListener(listener: OnClickListener) {
        mRetryView.setOnClickListener(listener)
    }



}
