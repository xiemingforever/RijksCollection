package com.apprecipe.rijkscollection.api

import com.apprecipe.rijkscollection.BuildConfig
import com.apprecipe.rijkscollection.data.ArtDetailResponse
import com.apprecipe.rijkscollection.data.ArtListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val NETWORK_PAGE_SIZE = 20

interface RijksAPI {

    @GET("collection")
    suspend fun getArtList(
        @Query("key") key: String = BuildConfig.RIJKS_API_KEY,
        @Query("toppieces") topPieces: Boolean = true,
        @Query("imgonly") imgonly: Boolean = true,
        @Query("p") page: Int,
        @Query("ps") perPage: Int = NETWORK_PAGE_SIZE,
    ): ArtListResponse

    @GET("collection/{objectNumber}")
    suspend fun getArtDetail(
        @Path("objectNumber") objectNumber: String,
        @Query("key") key: String = BuildConfig.RIJKS_API_KEY,
    ): ArtDetailResponse

    companion object {
        private const val BASE_URL = "https://www.rijksmuseum.nl/api/en/"

        fun create(): RijksAPI {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RijksAPI::class.java)
        }
    }
}
