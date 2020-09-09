package com.luxoft.task.search.di

import androidx.annotation.NonNull
import com.luxoft.task.search.network.SearchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SearchRetrofitModule {

    @Provides
    @Singleton
    fun provideSearchRetrofit(@NonNull retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)
}