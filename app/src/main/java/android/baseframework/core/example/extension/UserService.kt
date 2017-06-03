package android.baseframework.core.example.extension

import android.baseframework.leancloud.repository.json.LCUser
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("login")
    fun login(@Body user: RequestBody): Observable<LCUser>
}
