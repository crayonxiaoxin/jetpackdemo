package com.github.viewmodel.network

import com.github.viewmodel.model.Photos
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET(PIXABAY_PATH)
    suspend fun getPhotos(
        @Query("key") key: String = PIXABAY_KEY,
        @Query("image_type") type: String = PIXABAY_IMAGE_TYPE,
        @Query("q") q: String,
        @Query("per_page") per_page: Int = PIXABAY_DEFAULT_PER_PAGE,
        @Query("page") page: Int
    ): Photos

    companion object {
        const val PIXABAY_URL = "https://pixabay.com/"
        const val PIXABAY_PATH = "api"
        const val PIXABAY_KEY = "18355226-b35903eca843b11193c92fac2"
        const val PIXABAY_IMAGE_TYPE = "photo"
        const val PIXABAY_DEFAULT_PER_PAGE = 10

        operator fun invoke(): PhotoService {
            val client = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }
                ).build()
            return Retrofit.Builder()
                .baseUrl(PIXABAY_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PhotoService::class.java)
        }
    }
}