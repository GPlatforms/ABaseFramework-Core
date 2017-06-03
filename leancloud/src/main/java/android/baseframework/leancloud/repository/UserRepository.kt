package android.baseframework.leancloud.repository

import android.baseframework.core.repository.config.RetrofitManager
import android.baseframework.leancloud.repository.json.LCObject
import android.baseframework.leancloud.repository.json.LCUser
import android.baseframework.leancloud.repository.service.UserService
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody


object UserRepository {

    fun requestSmsCode(mobile: String): Observable<LCObject<*>> {

        val requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"mobilePhoneNumber\": \"$mobile\"}")
        val service = RetrofitManager.getRxRetrofit().create(UserService::class.java)

        return service.requestSmsCode(requestBody)
    }

    fun me(): Observable<LCUser> {

        val service = RetrofitManager.getRxRetrofit().create(UserService::class.java)

        return service.me()
    }
}