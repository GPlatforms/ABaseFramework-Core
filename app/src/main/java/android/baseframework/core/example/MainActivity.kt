package android.baseframework.core.example

import android.baseframework.core.base.BaseCoreActivity
import android.baseframework.core.config.Api
import android.baseframework.core.example.extension.getNextNextUrl
import android.baseframework.core.utils.startActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : BaseCoreActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Toast.makeText(this, Api.getNextNextUrl(), Toast.LENGTH_SHORT).show()
        startActivity(SimpleWebViewActivity::class.java)
    }


}
