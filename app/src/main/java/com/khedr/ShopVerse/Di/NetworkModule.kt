package com.khedr.ShopVerse.Di

import android.content.Context
import com.google.gson.GsonBuilder
import com.khedr.ShopVerse.data.network.ApiService
import com.khedr.ShopVerse.data.network.AuthInterceptor
import com.khedr.ShopVerse.util.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://student.valuxapps.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(@ApplicationContext context: Context): AuthInterceptor {
        val deviceLanguage = getDeviceLanguage()
        return AuthInterceptor(language = deviceLanguage)
    }
    @Provides
    @Singleton
    fun appPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }

    private fun getDeviceLanguage(): String {
        val deviceLanguage = Locale.getDefault().language
        return if (deviceLanguage == "ar") "ar" else "en"
    }
}
