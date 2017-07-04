package android.baseframework.core.base.webview.widget

import android.baseframework.core.R
import android.baseframework.core.utils.dp2px
import android.baseframework.core.utils.dp2pxf
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ProgressBar

/**
 * Created by Neil Zheng on 2017/7/3.
 */

open class DefaultProgressBar : ProgressBar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        elevation = dp2pxf(context, 2f)
        progressTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorAccent))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(dp2px(context, progressHeight()),
                MeasureSpec.EXACTLY))
    }

    open fun progressHeight(): Float {
        return 2f
    }
}
