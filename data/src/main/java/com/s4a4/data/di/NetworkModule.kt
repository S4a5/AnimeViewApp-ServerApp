package com.s4a4.data.di

import com.s4a4.core.UrlServer
import com.s4a4.data.AnilibriaService
import com.s4a4.data.AnimeVostService
import com.s4a4.data.KtorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Используйте ActivityComponent или другой, если необходимо
object AnimeVostModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    @Named("Ktor")
    fun provideRetrofitKtor( okHttpClient: OkHttpClient): Retrofit {
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