package com.valentinerutto.shamiri.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitClient {

        @OptIn(ExperimentalSerializationApi::class)
        fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {

            val contentType = "application/json".toMediaType()
            val converterFactory = Json.asConverterFactory(contentType)

            return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                .addConverterFactory(converterFactory).build()
        }

        fun createOkClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(getLoggingInterceptor())

            .build()
        }

        private fun getLoggingInterceptor(): Interceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return httpLoggingInterceptor
        }
    }
