package android.baseframework.core.example.model

import android.support.annotation.Keep

@Keep
data class User(var uid: String = "",
                var nickname: String = "")