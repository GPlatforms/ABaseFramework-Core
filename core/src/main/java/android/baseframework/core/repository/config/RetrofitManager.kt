package android.baseframework.core.repository.config

import android.baseframework.core.config.Api
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitManager {

    private fun buildBaseRetrofit(): Retrofit.Builder {
        val retrofitBuilder = Retrofit.Builder()
                .client(OkHttpClientManager.getInstance().createOkHttpClient())
        retrofitBuilder.baseUrl(Api.SERVER_URL)
        return retrofitBuilder
    }

    fun getRxRetrofit(): Retrofit {
        return buildBaseRetrofit()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getRxRetrofitWithFormatDate(): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        return buildBaseRetrofit()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getRxScalarsRetrofit(): Retrofit {
        return buildBaseRetrofit()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}