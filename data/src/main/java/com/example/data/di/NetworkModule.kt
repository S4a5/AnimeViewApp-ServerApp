package com.example.data.di

import com.example.data.AnilibriaService
import com.example.data.AnimeVostService
import com.example.data.KtorService
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
    @Named("AnimeVost")
    fun provideRetrofitAnimeVost(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.animevost.org/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("Anilibria")
    fun provideRetrofitAnilibria(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.anilibria.tv/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    @Named("Ktor")
    fun provideRetrofitKtor(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.106:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //    @Provides
//    @Singleton
//    fun provideAnimeVostService(@Named("AnimeVost") retrofit: Retrofit): AnimeVostService {
//        return retrofit.create(AnimeVostService::class.java)
//    }
//    @Provides
//    @Singleton
//    fun provideAnilibriaService(@Named("Anilibria") retrofit: Retrofit): AnilibriaService {
//        return retrofit.create(AnilibriaService::class.java)
//    }
    @Provides
    @Singleton
    fun provideKtorService(@Named("Ktor")retrofit: Retrofit): KtorService {
        return retrofit.create(KtorService::class.java)
    }
}