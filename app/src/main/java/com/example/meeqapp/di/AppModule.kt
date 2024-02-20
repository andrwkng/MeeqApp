package com.example.meeqapp.di

import com.example.meeqapp.data.FlagStore
import com.example.meeqapp.data.PredictionStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providePredictionStore(
        flagStore: FlagStore
    ): PredictionStore {
        return PredictionStore(flagStore)
    }
}