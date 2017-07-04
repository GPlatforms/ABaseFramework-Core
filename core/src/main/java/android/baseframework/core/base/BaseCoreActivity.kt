package android.baseframework.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.ikidou.fragmentBackHandler.BackHandlerHelper

abstract class BaseCoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this@BaseCoreActivity)) {
            super.onBackPressed();
        }
    }
}