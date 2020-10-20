package com.github.viewmodel.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    const val PIXABAY_URL = "https://pixabay.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        ).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(PIXABAY_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(className: Class<T>): T = retrofit.create(className)

    // 实化泛型 使得泛型可以使用 T::class.java
    inline fun <reified T> create(): T = create(T::class.java)
}