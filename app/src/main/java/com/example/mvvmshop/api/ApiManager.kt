package com.example.mvvmshop.api

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mvvmshop.MyApp
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    var api: Api? = null

    fun getApiInstance(): Api{
        if (api == null){
            api = getRetrofit().create(Api::class.java)
        }
        return api!!
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://osonsavdo.herokuapp.com/api/")
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            ChuckerInterceptor.Builder(MyApp.app)
                .collector(ChuckerCollector(MyApp.app))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        ).build()
    }
}