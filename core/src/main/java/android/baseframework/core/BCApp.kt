package android.baseframework.core

import android.app.Application
import android.baseframework.core.config.BCGlobal
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.jayfeng.lesscode.core.DisplayLess
import com.jayfeng.lesscode.headerbar.HeaderBarConfig


open class BCApp: Application() {

    override fun onCreate() {
        super.onCreate()

        BCGlobal.context = this

        initHeaderBar()
    }

    open fun initHeaderBar() {

        HeaderBarConfig.getInstance()
                // header
                .headerHeight(applicationContext.resources.getDimension(R.dimen.bc_header_height).toInt())
                .headerBackgroundDrawable(ColorDrawable(Color.parseColor("#1abc9c")))
                .headerShadowHeight(DisplayLess.`$dp2px`(16f))
                // title
                .titleTextColor(Color.BLUE)
                .titleTextSize(24)
                .subtitleTextColor(Color.RED)
                // item
                .itemTextSize(10)
                .build()
    }
}