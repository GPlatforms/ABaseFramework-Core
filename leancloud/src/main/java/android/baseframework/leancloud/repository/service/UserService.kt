package android.baseframework.leancloud.repository.service

import android.baseframework.leancloud.repository.json.LCObject
import android.baseframework.leancloud.repository.json.LCUser
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface UserService {

    @POST("requestSmsCode")
    fun requestSmsCode(@Body mobile: RequestBody): Observable<LCObject<*>>

    @GET("users/me")
    fun me(): Observable<LCUser>
}