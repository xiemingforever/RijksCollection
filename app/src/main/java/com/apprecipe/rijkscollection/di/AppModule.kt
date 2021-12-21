package com.apprecipe.rijkscollection.di

import com.apprecipe.rijkscollection.api.RijksAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRijksAPI(): RijksAPI = RijksAPI.create()
}
