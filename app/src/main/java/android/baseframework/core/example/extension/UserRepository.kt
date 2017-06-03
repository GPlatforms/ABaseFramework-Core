package android.baseframework.core.example.extension

import android.baseframework.core.repository.config.RetrofitManager
import android.baseframework.leancloud.repository.UserRepository
import android.baseframework.leancloud.repository.json.LCUser
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody


fun UserRepository.login(): Observable<LCUser> {

    val requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"username\": \"" + "aaaa" + "\", \"password\":\"" + "bbb" + "\"}")
    val service = RetrofitManager.getRxRetrofit().create(UserService::class.java)

    return service.login(requestBody)
}