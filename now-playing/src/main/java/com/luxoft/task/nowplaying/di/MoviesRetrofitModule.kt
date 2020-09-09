package com.luxoft.task.nowplaying.di

import androidx.annotation.NonNull
import com.luxoft.task.nowplaying.network.MoviesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MoviesRetrofitModule {

    @Provides
    @Singleton
    fun provideMoviesRetrofit(@NonNull retrofit: Retrofit): MoviesApi =
        retrofit.create(MoviesApi::class.java)
}