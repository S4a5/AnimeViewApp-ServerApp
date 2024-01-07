package com.s4a4.data.di

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.s4a4.core.UrlServer
import com.s4a4.data.AnilibriaService
import com.s4a4.data.AnimeVostService
import com.s4a4.data.KtorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

class LoggingInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        try {
            val response = chain.proceed(request)

            // Дополнительная обработка успешного ответа, если необходимо

            return response .newBuilder()
                .build()
        } catch (e: IOException) {
            // Обработка ошибок ввода-вывода, включая SocketTimeoutException
            e.printStackTrace()
            val msg = when (e) {
                is SocketTimeoutException -> "Timeout - Please check your internet connection"
                is UnknownHostException -> "Unable to make a connection. Please check your internet"
                else -> "Server is unreachable, please try again later."
            }


            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body(e.toString().toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        }
    }
}
@Module
@InstallIn(SingletonComponent::class)
object AnimeVostModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .addInterceptor { LoggingInterceptor().intercept(it) }
            .build()
    }

    @Provides
    @Singleton
    @Named("Ktor")
    fun provideRetrofitKtor(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UrlServer)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

    @Provides
    @Singleton
    fun provideKtorService(@Named("Ktor") retrofit: Retrofit): KtorService {
        return retrofit.create(KtorService::class.java)
    }
}