package android.baseframework.core.repository.config

import android.baseframework.core.config.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitManager {

    fun getRetrofit(): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
                .client(OkHttpClientManager.getInstance().createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
        retrofitBuilder.baseUrl(Api.SERVER_URL)
        return retrofitBuilder.build()
    }

    fun getRxRetrofit(): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
                .client(OkHttpClientManager.getInstance().createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofitBuilder.baseUrl(Api.SERVER_URL)
        return retrofitBuilder.build()
    }
}