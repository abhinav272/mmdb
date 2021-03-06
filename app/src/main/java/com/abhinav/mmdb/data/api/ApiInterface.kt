package com.abhinav.mmdb.data.api

import com.abhinav.mmdb.BuildConfig
import com.abhinav.mmdb.BuildConfig.BASE_URL
import com.abhinav.mmdb.data.model.Configurations
import com.abhinav.mmdb.data.model.GenreResponse
import com.abhinav.mmdb.data.model.NowPlayingResponse
import com.abhinav.mmdb.data.model.TrendingResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiInterface {
    companion object {
        fun getAPIService(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(getClient())
                .build()
                .create(ApiInterface::class.java)
        }

        private fun getClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = when {
                        BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                        else -> HttpLoggingInterceptor.Level.NONE
                    }
                })
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()

                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.tmdb_api_key)
//                                .addQueryParameter("api_key", "io")
                        .build()

                    // Request customization: add request headers
                    val requestBuilder = original.newBuilder()
                        .url(url)

                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
            return httpClient.build()
        }
    }

    @GET("configuration")
    fun fetchConfigurations(): Deferred<Response<Configurations>>

    @GET("trending/{media_type}/{time_window}")
    fun getTrending(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("page") pageNo: Int
    ): Deferred<Response<TrendingResponse>>

    @GET("genre/movie/list")
    fun getGenreMasterData(): Deferred<Response<GenreResponse>>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("page") pageNo: Int,
        @Query("language") language: String
    ): Deferred<Response<NowPlayingResponse>>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("page") pageNo: Int,
        @Query("language") language: String
    ): Deferred<Response<NowPlayingResponse>>

}