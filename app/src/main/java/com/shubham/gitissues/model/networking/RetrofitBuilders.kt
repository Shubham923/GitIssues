package com.shubham.gitissues.model.networking

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun buildClient() : OkHttpClient =
    OkHttpClient.Builder().build()


fun buildRetrofit() : Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}



fun buildApiService() : RemoteApiService =
    buildRetrofit().create(RemoteApiService :: class.java)