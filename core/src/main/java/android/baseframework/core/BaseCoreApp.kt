package android.baseframework.core

import android.app.Application
import android.baseframework.core.config.Global


open class BaseCoreApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Global.context = this
    }
}