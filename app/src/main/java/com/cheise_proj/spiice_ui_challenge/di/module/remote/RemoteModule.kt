package com.cheise_proj.spiice_ui_challenge.di.module.remote

import com.cheise_proj.data.source.remote.MessageRemoteSource
import com.cheise_proj.data.source.remote.PostRemoteSource
import com.cheise_proj.data.source.remote.UserRemoteSource
import com.cheise_proj.remote.api.ApiService
import com.cheise_proj.remote.source.MessageRemoteSourceImpl
import com.cheise_proj.remote.source.PostRemoteSourceImpl
import com.cheise_proj.remote.source.UserRemoteSourceImpl
import com.cheise_proj.spiice_ui_challenge.common.SPIICE_BASE_URL
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {
    @Module
    interface Binders {


        //region USER
        @Binds
        fun bindUserRemoteSource(userRemoteSourceImpl: UserRemoteSourceImpl): UserRemoteSource
        //endregion

        //region POST
        @Binds
        fun bindPostRemoteSource(postRemoteSourceImpl: PostRemoteSourceImpl): PostRemoteSource
        //endregion

        //region MESSAGE
        @Binds
        fun bindMessageRemoteSource(messageRemoteSourceImpl: MessageRemoteSourceImpl): MessageRemoteSource
        //endregion
    }

    @Suppress("SpellCheckingInspection")
    @Provides
    fun provideOkttpClient(
        tokenService: TokenService,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .authenticator(tokenAuthenticator)
            .addInterceptor(tokenService)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(getBaseUrl())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private fun getBaseUrl() = SPIICE_BASE_URL
}