package com.github.dudgns0507.mvvm.di

import android.content.Context
import com.github.dudgns0507.core.Constant
import com.github.dudgns0507.core.util.network.NetworkInterceptor
import com.github.dudgns0507.core.util.network.SafeCallAdapterFactory
import com.github.dudgns0507.data.jsonplaceholder.JsonService
import com.github.dudgns0507.mvvm.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val timeoutRead = 30   //In seconds
    private val timeoutConnect = 30   //In seconds
    private val contentType = "Content-Type"
    private val contentTypeValue = "application/json"

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
//            .add(MyKotlinJsonAdapterFactory())
//            .add(MyStandardJsonAdapters.FACTORY)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(logger)
            .addInterceptor(NetworkInterceptor(context))
            .connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constant.url)
            .addCallAdapterFactory(SafeCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): JsonService {
        return retrofit.create(JsonService::class.java)
    }
}
