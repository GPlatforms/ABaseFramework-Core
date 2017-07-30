package android.baseframework.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseCoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}