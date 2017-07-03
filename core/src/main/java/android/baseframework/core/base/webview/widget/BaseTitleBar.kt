package android.baseframework.core.base.webview.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet

/**
 * Created by Neil Zheng on 2017/7/3.
 */

abstract class BaseTitleBar : Toolbar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)



}
