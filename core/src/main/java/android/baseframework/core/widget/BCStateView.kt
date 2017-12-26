package android.baseframework.core.widget

import android.baseframework.core.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*

class BCStateView @JvmOverloads constructor(context: Context?,
                                            attrs: AttributeSet? = null,
                                            defStyle: Int = 0): RelativeLayout(context, attrs, defStyle) {

    init {
        init(attrs, defStyle)
    }

    val customContainer by lazy { findViewById<FrameLayout>(R.id.bc_state_custom_container) }
    val loadingContainer by lazy { findViewById<View>(R.id.bc_state_loading_container) }
    val emptyContainer by lazy { findViewById<View>(R.id.bc_state_empty_container) }
    val errorContainer by lazy { findViewById<View>(R.id.bc_state_error_container) }

    val loadingProgressView by lazy { findViewById<ProgressBar>(R.id.bc_state_loading_progress) }
    val loadingTextView by lazy { findViewById<TextView>(R.id.bc_state_loading_text) }
    val emptyIconView by lazy { findViewById<ImageView>(R.id.bc_state_empty_icon) }
    val emptyTextView by lazy { findViewById<TextView>(R.id.bc_state_empty_text) }
    val errorIconView by lazy { findViewById<ImageView>(R.id.bc_state_error_icon) }
    val errorTextView by lazy { findViewById<TextView>(R.id.bc_state_error_text) }
    val errorRetryView by lazy { findViewById<Button>(R.id.bc_state_error_retry) }


    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.bc_view_state, this, true)
    }

    fun setCustomView(customView: View) {
        customContainer.removeAllViews()
        customContainer.addView(customView)
    }

    fun showCustomView() {
        customContainer.visibility = View.VISIBLE
        loadingContainer.visibility = View.GONE
        emptyContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
    }

    fun showLoading() {
        customContainer.visibility = View.GONE
        loadingContainer.visibility = View.VISIBLE
        emptyContainer.visibility = View.GONE
        errorContainer.visibility = View.GONE
    }

    fun showEmpty() {
        customContainer.visibility = View.GONE
        loadingContainer.visibility = View.GONE
        emptyContainer.visibility = View.VISIBLE
        errorContainer.visibility = View.GONE
    }

    fun showError() {
        customContainer.visibility = View.GONE
        loadingContainer.visibility = View.GONE
        emptyContainer.visibility = View.GONE
        errorContainer.visibility = View.VISIBLE
    }

    fun setErrorRetryClickListener(listener: OnClickListener) {
        errorRetryView.setOnClickListener(listener)
    }

    fun setLoadingText(text: String) {
        loadingTextView.text = text
    }

    fun setEmptyText(text: String) {
        emptyTextView.text = text
    }

    fun setEmptyIcon(icon: Drawable) {
        emptyIconView.setImageDrawable(icon)
    }

    fun setEmptyIcon(icon: Int) {
        emptyIconView.setImageResource(icon)
    }

    fun setErrorText(text: String) {
        errorTextView.text = text
    }

    fun setErrorIcon(icon: Drawable) {
        errorIconView.setImageDrawable(icon)
    }

    fun setErrorIcon(icon: Int) {
        errorIconView.setImageResource(icon)
    }

}
